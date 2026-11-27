package ad.cfp.viatges

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize
import java.util.FormattableFlags
@Parcelize

data class Viatge(
    val id: Int,
    val destinacio: String,
    val dataSortida: String,
    val dataTornada: String,
    val preu: Double,
    val imatge: String,
    var preferit: Boolean = false
): Parcelable



