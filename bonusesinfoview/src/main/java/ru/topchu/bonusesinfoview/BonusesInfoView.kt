package ru.topchu.bonusesinfoview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.shimmer.ShimmerFrameLayout
import ru.topchu.bonusesinfoview.utils.Extensions.formatDate

class BonusesInfoView constructor(
    context: Context,
    attributeSet: AttributeSet
): CardView(context, attributeSet) {

    init {
        inflate(context, R.layout.compound_view_bonuses_info, this)
        this.findViewById<ShimmerFrameLayout>(R.id.shimmer_layout).startShimmer()
    }

    private var _currentBonuses: Int = 0
    private var _burningDate: String = ""
    private var _burningBonuses: Int = 0

    @SuppressLint("SetTextI18n")
    fun setData(currentBonuses: Int, burningDate: String, burningBonuses: Int) {
        _currentBonuses = currentBonuses
        _burningDate = burningDate
        _burningBonuses = burningBonuses
        this.findViewById<TextView>(R.id.current_bonuses).text =
            context.resources.getQuantityString(
                R.plurals.plurals_bonuses,
                currentBonuses,
                currentBonuses
            )
        this.findViewById<TextView>(R.id.burning_date).text = "${burningDate.formatDate()} сгорит"
        this.findViewById<TextView>(R.id.burning_bonuses).text =
            context.resources.getQuantityString(
                R.plurals.plurals_bonuses,
                burningBonuses,
                burningBonuses
            )
        this.findViewById<ConstraintLayout>(R.id.content).visibility = View.VISIBLE
        this.findViewById<ShimmerFrameLayout>(R.id.shimmer_layout).apply {
            stopShimmer()
            visibility = View.GONE
        }
    }

    fun setActionButtonClickListener(listener: OnClickListener) {
        this.findViewById<ImageView>(R.id.button_action).setOnClickListener(listener)
    }

}