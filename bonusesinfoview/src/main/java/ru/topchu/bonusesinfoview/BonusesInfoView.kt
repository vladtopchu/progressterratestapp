package ru.topchu.bonusesinfoview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import ru.topchu.bonusesinfoview.utils.Extensions.formatDate

class BonusesInfoView constructor(context: Context, attributeSet: AttributeSet): CardView(context, attributeSet) {

    init {
        inflate(context, R.layout.compound_view_bonuses_info, this)
    }

    var currentBonuses: Int = 0
        set(value){
            this.findViewById<TextView>(R.id.current_bonuses).text =
                context.resources.getQuantityString(
                    R.plurals.plurals_bonuses, value, value
                )
            field = value
        }

    private var _burningDate: String = ""
    private var _burningBonuses: Int = 0

    @SuppressLint("SetTextI18n")
    fun setBurningText(burningDate: String, burningBonuses: Int) {
        _burningDate = burningDate
        _burningBonuses = burningBonuses
        this.findViewById<TextView>(R.id.burning_date).text = "${burningDate.formatDate()} сгорит"
        this.findViewById<TextView>(R.id.burning_bonuses).text = context.resources.getQuantityString(R.plurals.plurals_bonuses, burningBonuses, burningBonuses)
    }

    fun setActionButtonClickListener(listener: OnClickListener) {
        this.findViewById<ImageView>(R.id.button_action).setOnClickListener(listener)
    }

}