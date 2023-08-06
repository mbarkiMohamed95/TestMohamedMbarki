package com.example.CoTest.tools.pagination

import androidx.paging.PagingSource

import com.example.domain.repo.user.model.UserModelDto

abstract  class PagerService: PagingSource<Int, UserModelDto>()
