# Multi Objectives Optimization using NSGA-ii

A solution to the multi objectives optimization problem using the Non-dominated search sorting algorithm

For this, each equation must be manually coded into the code. Each function (objectives) must extends the BaseObjectives class.

## Bench mark

The solution to the objectives found in here: 

http://people.ee.ethz.ch/~sop/download/supplementary/testproblems/zdt1/index.php
Minimize:

* f_1 = x_1

* f_2 = g * (1 - Math.Sqrt(f_1 / g)

* g = 1 + 9 / (n-1) * (x_2 + x_3 + ... + x_n)

After running the methods for 2000 generations, with initial population of 200, here is the result, with n = 3:

* f1 = 0.00392156862745098 , f2 = 0.9373775708914851 , gx = 1.0|-Variables-| , 0.00392156862745098 , 0.0 , 0.0

* f1 = 0.8470588235294118 , f2 = 0.07964201338315546 , gx = 1.0|-Variables-| , 0.8470588235294118 , 0.0 , 0.0

* f1 = 0.7254901960784313 , f2 = 0.14824287729515795 , gx = 1.0|-Variables-| , 0.7254901960784313 , 0.0 , 0.0

* f1 = 1.0 , f2 = 0.0 , gx = 1.0|-Variables-| , 1.0 , 0.0 , 0.0

* f1 = 0.34901960784313724 , f2 = 0.43139818073459485 , gx = 1.0313725490196077|-Variables-| , 0.34901960784313724 , 0.0 , 0.00784313725490196

* f1 = 0.5647058823529412 , f2 = 0.24853085069782066 , gx = 1.0|-Variables-| , 0.5647058823529412 , 0.0 , 0.0

* f1 = 0.6862745098039216 , f2 = 0.171583130420486 , gx = 1.0|-Variables-| , 0.6862745098039216 , 0.0 , 0.0

* f1 = 0.28627450980392155 , f2 = 0.4649537311559667 , gx = 1.0|-Variables-| , 0.28627450980392155 , 0.0 , 0.0

* f1 = 0.4745098039215686 , f2 = 0.31115327980633556 , gx = 1.0|-Variables-| , 0.4745098039215686 , 0.0 , 0.0

* f1 = 0.9372549019607843 , f2 = 0.048184379178037286 , gx = 1.0313725490196077|-Variables-| , 0.9372549019607843 , 0.0 , 0.00784313725490196

* f1 = 0.9764705882352941 , f2 = 0.011834736374884414 , gx = 1.0|-Variables-| , 0.9764705882352941 , 0.0 , 0.0

* f1 = 0.7803921568627451 , f2 = 0.11660192616083598 , gx = 1.0|-Variables-| , 0.7803921568627451 , 0.0 , 0.0

* f1 = 0.6313725490196078 , f2 = 0.20541045248530498 , gx = 1.0|-Variables-| , 0.6313725490196078 , 0.0 , 0.0

* f1 = 0.2627450980392157 , f2 = 0.48741332631523115 , gx = 1.0|-Variables-| , 0.2627450980392157 , 0.0 , 0.0
