package com.artist.wea.data

data class JoinUser(
    val id:String,
    val password:String,
    val name:String,
    val email:String,
    val terms:Boolean = false,
    val checkId:Boolean = false,
    val checkEmail:Boolean = false
)
