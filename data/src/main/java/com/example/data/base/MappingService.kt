package com.example.data.base


interface MappingService<Input:Any,Output:Any> {

    fun mapInputToOutput(input: Input) : Output

    fun mapInputToOutput(inputList : List<Input >) : List<Output> = inputList.map { input->
        mapInputToOutput(input)
    }

}