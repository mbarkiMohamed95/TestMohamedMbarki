package com.example.domain.tools.paging

import androidx.paging.PagingSource

import com.example.domain.loadUseCase.model.UserModel

abstract  class PagerService: PagingSource<Int, UserModel>()
