import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class WeatherResponse(
    @SerializedName("cod")
    @Expose
    var cod: String,
    @SerializedName("message")
    @Expose
    var message: Double? = null,
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,
    @SerializedName("list")
    @Expose
    var list: List<WeatherrList>? = null,
    @SerializedName("city")
    @Expose
    var city: City? = null
):Serializable


data class City (
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("coord")
    @Expose
    var coord: WeatherCoord? = null,
    @SerializedName("country")
    @Expose
    var country: String? = null

):Serializable


data class WeatherCoord (
    @SerializedName("lat")
    @Expose
    var lat: Double? = null,
    @SerializedName("lon")
    @Expose
    var lon: Double? = null

):Serializable


data class WeatherrList (
    @SerializedName("dt")
    @Expose
    var dt: Int? = null,
    @SerializedName("main")
    @Expose
    var main: WeatherMain? = null,
    @SerializedName("weather")
    @Expose
    var weather: List<Weatherrr>? = null,
    @SerializedName("clouds")
    @Expose
    var clouds: WeatherClouds? = null,
    @SerializedName("wind")
    @Expose
    var wind: WeatherWind? = null,
    @SerializedName("sys")
    @Expose
    var sys: WeatherSys? = null,
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null,
    @SerializedName("rain")
    @Expose
    var rain: WeatherRain? = null,
    @SerializedName("snow")
    @Expose
    var snow: WeatherSnow? = null

):Serializable

data class WeatherMain (
    @SerializedName("temp")
    @Expose
    var temp: Double? = null,
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null,
    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null,
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Double? = null,
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Double? = null,
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Double? = null
):Serializable

data class Weatherrr (
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("main")
    @Expose
    var main: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("icon")
    @Expose
    var icon: String? = null

):Serializable

data class WeatherClouds (
    @SerializedName("all")
    @Expose
    var all: Int? = null

):Serializable

data class WeatherWind (
    @SerializedName("speed")
    @Expose
    var speed: Double? = null,
    @SerializedName("deg")
    @Expose
    var deg: Double? = null

):Serializable

data class WeatherSys (
    @SerializedName("pod")
    @Expose
    var pod: String? = null

):Serializable

data class WeatherRain (
    @SerializedName("3h")
    @Expose
    private var _3h: Double? = null
):Serializable

data class WeatherSnow (
    @SerializedName("3h")
    @Expose
    private var _3h: Double? = null

):Serializable