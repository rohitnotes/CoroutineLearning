package io.github.zoha131.coroutinelearning

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel(){

    val mainRepository = MainRepository() // normally this would come from DI

    /*private val _intLiveData = MutableLiveData<Int>()
    val intLiveData: LiveData<Int>
        get() = _intLiveData

    init {
        viewModelScope.launch {
            // Coroutine that will be canceled when
            // the ViewModel is cleared.
            val data = mainRepository.getData()
            _intLiveData.value = data
        }
    }*/

    val intLiveData:LiveData<Int> = liveData {

        if(latestValue == null){
            delay(1000)
            emit(mainRepository.getData())
        }

    }

    val strLiveData = liveData {
        val liveData = mainRepository.getStrLiveData()
        emitSource(liveData)

        delay(1000)
        val sLiveData = mainRepository.getAnotherStrLiveData()
        emitSource(sLiveData)
    }


}

class MainRepository{
    suspend fun getData(): Int {
        delay(1000)
        return 6
    }

    suspend fun getStrLiveData():LiveData<String>{
        delay(1000)
        return MutableLiveData("LiveData")
    }

    suspend fun getAnotherStrLiveData():LiveData<String>{
        delay(1000)
        return MutableLiveData("Second LiveDAta")
    }
}

