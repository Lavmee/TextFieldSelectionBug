package tech.annexflow.textfieldselectionbug

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleCurrencySymbol
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

internal data class DecimalFormat(
    val positiveSuffix: String,
    val negativeSuffix: String,
    val positivePrefix: String,
    val negativePrefix: String,
    val groupingSize: Int,
    val decimalFormatSymbols: DecimalFormatSymbols,
)

internal data class DecimalFormatSymbols(
    val groupingSeparator: Char,
    val decimalSeparator: Char,
    val currencySymbol: String,
)

internal fun getDefaultLocaleDecimalFormatByCurrencyCode(currencyCode: String): DecimalFormat {
    val locale = NSLocale(currencyCode)
    val numberFormatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterCurrencyStyle
    }
    val currencySymbol = locale.displayNameForKey(NSLocaleCurrencySymbol, currencyCode)
        ?: numberFormatter.currencySymbol

    return DecimalFormat(
        positivePrefix = numberFormatter.positivePrefix,
        positiveSuffix = numberFormatter.positiveSuffix,
        negativePrefix = numberFormatter.negativePrefix,
        negativeSuffix = numberFormatter.negativeSuffix,
        groupingSize = numberFormatter.groupingSize.toInt(),
        decimalFormatSymbols = DecimalFormatSymbols(
            groupingSeparator = numberFormatter.groupingSeparator.first(),
            decimalSeparator = numberFormatter.decimalSeparator.first(),
            currencySymbol = currencySymbol
        )
    )
}