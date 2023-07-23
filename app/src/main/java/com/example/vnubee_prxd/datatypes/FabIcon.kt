package com.example.vnubee_prxd.datatypes

import com.example.vnubee_prxd.R

class FabIcon{

    private val iconList = listOf(
        Icon(id = 'a', item = R.drawable.a),
        Icon(id = 'b', item = R.drawable.b),
        Icon(id = 'c', item = R.drawable.c),
        Icon(id = 'd', item = R.drawable.d),
        Icon(id = 'e', item = R.drawable.e),
        Icon(id = 'f', item = R.drawable.f),
        Icon(id = 'g', item = R.drawable.g),
        Icon(id = 'h', item = R.drawable.h),
        Icon(id = 'i', item = R.drawable.i),
        Icon(id = 'j', item = R.drawable.j),
        Icon(id = 'k', item = R.drawable.k),
        Icon(id = 'l', item = R.drawable.l),
        Icon(id = 'm', item = R.drawable.m),
        Icon(id = 'n', item = R.drawable.n),
        Icon(id = 'o', item = R.drawable.o),
        Icon(id = 'p', item = R.drawable.p),
        Icon(id = 'q', item = R.drawable.q),
        Icon(id = 'r', item = R.drawable.r),
        Icon(id = 's', item = R.drawable.s),
        Icon(id = 't', item = R.drawable.t),
        Icon(id = 'u', item = R.drawable.u),
        Icon(id = 'v', item = R.drawable.v),
        Icon(id = 'w', item = R.drawable.w),
        Icon(id = 'x', item = R.drawable.x),
        Icon(id = 'y', item = R.drawable.y),
        Icon(id = 'z', item = R.drawable.z),
    )

    fun getIcon(name: String): Int{
        iconList.forEach { icon ->
            if (icon.id == name.first().lowercaseChar()){
                return icon.item
            }
        }
        return R.drawable.unknown
    }
}
