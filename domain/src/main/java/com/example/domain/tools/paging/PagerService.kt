package com.example.domain.tools.paging

import androidx.paging.PagingSource

import com.example.domain.repo.model.UserModelDto

abstract  class PagerService: PagingSource<Int, UserModelDto>()
