package methods

import kotlinx.coroutines.flow.MutableStateFlow

fun init (forecast: List<MutableStateFlow<String>>, step :Int, startIndex:Int){
    var counter = startIndex
    for (item in forecast){
        item.value = setText(counter)
        counter += step
    }

}