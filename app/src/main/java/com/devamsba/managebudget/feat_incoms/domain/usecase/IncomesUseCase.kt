package com.devamsba.managebudget.feat_incoms.domain.usecase

data class IncomesUseCase (

    var insertIncomes: InsertIncomes,
    var updateIncomes: UpdateIncomes,
    var deleteIncomes: DeleteIncomes,
    var FetchIncomes: FetchIncomes


    )