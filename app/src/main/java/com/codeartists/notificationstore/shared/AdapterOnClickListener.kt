package com.codeartists.notificationstore.shared

interface AdapterOnClickListener {
    fun onClickItem(position: Int, data: HashMap<String, Any>)
}