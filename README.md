Android MVVM Demo
=================
* Library : DataBinding, Retrofit2, RxJava2, RxAndroid2, retrofit-rxjava-adapter, retrolambda, recyclerView, glide
* [Google android-architecture](https://github.com/googlesamples/android-architecture) 참고
* Github api 연동

## 1. DataBinding
* ButterKnife를 대체하는 뷰 바인딩 라이브러리(Android 2.1(API 레벨 7 이상) 사용가능)
* ButterKnife는 Reflection을 사용, DataBinding은 Annotation Processing을 사용
* MVVM에 데이터바인딩을 사용하여 개발 시 View에 대한 종속성을 줄인다.

## 2. MVP의 문제
1. Presenter가 MVC의 Controller처럼 비지니스 로직 코드가 쏠리고, Interface가 많아지는 문제가 있다.
2. MVP에서는 하나의 View에 하나의 Presenter가 1:1로 대응하기 때문에 동일한 기능을하는 Presenter가 중복 생성될 수 있다.

## 3. MVP vs MVVM

#### 3-1. 공통점
* View is not aware of the Model. Presenter(or BaseViewModel) update the Model. (뷰는 모델의 존재를 모른다. 프레젠터나 뷰모델이 모델로부터 값을 받아 업데이트 시킨다.)

#### 3-2. 차이점
* MVP : One-to-One relationship between View and Presenter. (뷰와 프레젠터가 1:1 관계를 가진다. -> Interface가 많아지고, Presenter의 기능이 중복될 수 있다.), View holds the reference to its Presenter and Presenter is aware of its View. (뷰는 프레젠터의 레퍼런스를 가지고, 프레젠터는 뷰의 존재를 알고 있다.)
* MVVM : One-to-Many relationship between BaseViewModel and View. (뷰모델과 뷰는 1:다 관계를 가진다. -> 뷰모델은 계속 재활용될 수 있다.), BaseViewModel doesn't have any knowledge of it View. (뷰모델은 뷰의 존재를 모른다. (뷰는 뷰모델에 대한 레퍼런스를 가진다.))
