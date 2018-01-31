package fer.kotlin.weatherapp.data.twitter
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by "flopez" on 31/01/2018.
 */

data class TwitterDTOfromJSON(
		val id: String, //1076408665
		val name: String, //AEMET_País Vasco
		val screen_name: String, //AEMET_PaisVasco
		val description: String, //Servicio oficial de la Agencia Estatal de Meteorología (AEMET) en el País Vasco
		val followers_count: String, //7701
		val friends_count: String, //89
		val permanent: Boolean, //false
		val lang: String, //es
		val type: String, //meteo
		val profile_img: String //https://pbs.twimg.com/profile_images/901108389543120896/ez2xKH2n_normal.jpg
)