package com.devamsba.managebudget.feat_categries.data.local

import androidx.room.Dao
import com.devamsba.managebudget.common.data.categories.enitiy.CategoriesEntity
import com.devamsba.managebudget.common.data.BaseDao


@Dao
interface CategoriesDao : BaseDao<CategoriesEntity> {

}