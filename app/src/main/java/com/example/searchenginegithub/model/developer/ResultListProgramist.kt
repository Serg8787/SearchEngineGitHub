package com.example.searchenginegithub.model.developer

import com.example.searchenginegithub.model.developer.ItemProgramist

class ResultListProgramist (
    val incomplete_results: Boolean,
    val items: List<ItemProgramist>,
    val total_count: Int
)