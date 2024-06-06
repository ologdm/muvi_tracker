package com.example.muvitracker.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


// source1 = 1, source2 = "abc" --- 1, "abc"
// source1 = 2 --- 2, "abc
// source1 = 50 --- 50, "abc"
// source2 = "hello" --- 50, "hello"


fun <T1 : Any, T2 : Any, R : Any?> combineLatest(  // operator
    source1: LiveData<T1>,
    source2: LiveData<T2>,
    combiner: Function2<T1, T2, R>
): LiveData<R> {
    val mediator = MediatorLiveData<R>()

    val combinerFunction = {
        val source1Value = source1.value
        val source2Value = source2.value
        if (source1Value != null && source2Value != null) {
            mediator.value = combiner(source1Value, source2Value)
        }
    }
    mediator.addSource(source1) { combinerFunction() }
    mediator.addSource(source2) { combinerFunction() }
    return mediator
}



// concatena i valori di entrambi i livedata in un singolo livedata
fun <T : Any> concat( // operator
    source1: LiveData<T>,
    source2: LiveData<T>,
): LiveData<T> {
    val mediator = MediatorLiveData<T>()

    mediator.addSource(source1) { mediator.value = it }
    mediator.addSource(source2) { mediator.value = it }
    return mediator
    // valore mediator e quello piu recente dei 2 livedata osservati
}