package com.devamsba.managebudget



class CommantedCode {

    /*
    *             incomesVsExpensesTextView.setOnClickListener {
                incomesPieChart.visibility = View.GONE
                expensesPieChart.visibility = View.GONE
                if (!lineChart.isVisible) {
                    lineChart.visibility = View.VISIBLE
                }
                binding?.lineChart?.animateX(1000, Easing.EaseInOutQuart)

                incomesVsExpensesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_expenses_layout
                    )
                )
                expensesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_gray_layout
                    )
                )
                incomesTextView.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_gray_layout
                    )
                )

            }
*/
    /*data class Score(
    val name: String,
    val score: Int,
)*/
/*    private fun showBarChart() {
        val valueList = ArrayList<Double>()
        val entries: ArrayList<BarEntry> = ArrayList()
        val title = "Title"

        //input data
        for (i in 0..5) {
            valueList.add(i * 100.1)
        }

        //fit the data into a bar
//        for (i in 0 until valueList.size) {
//            var j : Float = 1.0f + 1
//            var k : Float = 59.0f * i
////
//        }
        val barEntry = BarEntry(1.0f, 100.0f)
        val barEntry2 = BarEntry(2.0f, 200.0f)
        val barEntry3 = BarEntry(3.0f, 300.0f)
        val barEntry4 = BarEntry(4.0f, 400.0f)
        val barEntry5 = BarEntry(5.0f, 500.0f)
        entries.add(barEntry)
        entries.add(barEntry2)
        entries.add(barEntry3)
        entries.add(barEntry4)
        entries.add(barEntry5)
        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        initBarDataSet(barDataSet)
        initBarChart()
        binding?.barChart?.apply {
            setData(data)
            invalidate()
        }


    }

    private fun initBarChart() {
        //hiding the grey background of the chart, default false if not set
        binding?.barChart!!.setDrawGridBackground(false)
        //remove the bar shadow, default false if not set
        binding?.barChart!!.setDrawBarShadow(false)
        //remove border of the chart, default false if not set
        binding?.barChart!!.setDrawBorders(false)

        //remove the description label text located at the lower right corner
//        val description = Description()
//        description.setEnabled(false)
//        binding?.barChart?.setDescription(description)

        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        binding?.barChart?.animateY(1000)
        //setting animation for x-axis, the bar will pop up separately within the time we set
        binding?.barChart?.animateX(1000)
        val xAxis: XAxis = binding?.barChart?.getXAxis()!!
        //change the position of x-axis to the bottom
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //set the horizontal distance of the grid line
        xAxis.granularity = 1f
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false)
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false)
        val leftAxis: YAxis = binding?.barChart!!.getAxisLeft()
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false)
        val rightAxis: YAxis = binding?.barChart!!.getAxisRight()
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false)
        val legend: Legend = binding?.barChart!!.getLegend()
        //setting the shape of the legend form to line, default square shape
        legend.form = Legend.LegendForm.LINE
        //setting the text size of the legend
        legend.textSize = 11f
        //setting the alignment of legend toward the chart
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        //setting the stacking direction of legend
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        //setting the location of legend outside the chart, default false if not set
        legend.setDrawInside(false)
    }

    private fun initBarDataSet(barDataSet: BarDataSet) {
        //Changing the color of the bar
        barDataSet.color = Color.parseColor("#304567")
        //Setting the size of the form in the legend
        barDataSet.formSize = 15f
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(false)
        //setting the text size of the value of the bar
        barDataSet.valueTextSize = 12f
    }

    private fun initLineChart() {

//        hide grid lines
        binding?.lineChart?.axisLeft?.setDrawGridLines(false)
        val xAxis: XAxis = binding?.lineChart?.xAxis!!
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        binding?.lineChart?.axisRight?.isEnabled = false

        //remove legend
        binding?.lineChart?.legend?.isEnabled = false


        //remove description label
        binding?.lineChart?.description?.isEnabled = false


        //add animation
        binding?.lineChart?.animateX(1000, Easing.EaseInSine)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }

    private fun setDataToLineChart() {
        //now draw bar chart with dynamic data
        val entries: ArrayList<Entry> = ArrayList()
        val entries2: ArrayList<Entry> = ArrayList()

        scoreList = getScoreList()

        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(Entry(i.toFloat(), score.score.toFloat()))
        }//you can replace this data object with  your custom object
        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries2.add(Entry(i.toFloat(), 77.0f))
        }//you can replace this data object with  your custom object

        val lineDataSet = LineDataSet(entries, "Here1")
        val lineDataSet2 = LineDataSet(entries2, "Here2")
        lineDataSet.color = resources.getColor(R.color.green_500)
        lineDataSet2.color = resources.getColor(R.color.red_700)

        lineDataSet.setCircleColors(resources.getColor(R.color.purple_500))
        lineDataSet2.setCircleColors(resources.getColor(R.color.black))

        lineDataSet.circleRadius = 7.0f

        val data = LineData(lineDataSet, lineDataSet2)
        binding?.lineChart?.data = data


        binding?.lineChart?.invalidate()
    }

    private fun getScoreList(): ArrayList<Score> {
        scoreList.add(Score("John", 56))
        scoreList.add(Score("Rey", 75))
        scoreList.add(Score("Steve", 85))
        scoreList.add(Score("Kevin", 66))
        scoreList.add(Score("Jeff", 63))

        return scoreList
    }*/

}