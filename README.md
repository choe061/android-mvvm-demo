Android MVVM Demo
=================
* Library : DataBinding, Retrofit2, RxJava2, RxAndroid2, retrolambda, recyclerView, glide
* [Google android-architecture](https://github.com/googlesamples/android-architecture) 참고
* Github api 연동

## 1. DataBinding
* ButterKnife를 대체하는 뷰 바인딩 라이브러리(Android 2.1(API 레벨 7 이상) 사용가능)
* MVVM에 데이터바인딩을 사용하여 개발 시 View에 대한 종속성을 줄인다.

## 2. MVP의 문제
1. Presenter가 MVC의 Controller처럼 비지니스 로직 코드가 쏠리고, Interface가 많아지는 문제가 있다.
2. MVP에서는 하나의 View에 하나의 Presenter가 1:1로 대응하기 때문에 동일한 기능을하는 Presenter가 중복 생성될 수 있다.

## 3. MVP vs MVVM

#### 3-1. 공통점
* View is not aware of the Model. Presenter(or BaseViewModel) update the Model. <br/>
(뷰는 모델의 존재를 모른다. 프레젠터나 뷰모델이 모델로부터 값을 받아 업데이트 시킨다.)

#### 3-2. 차이점
* MVP : <br/>
One-to-One relationship between View and Presenter.<br/>
(뷰와 프레젠터가 1:1 관계를 가진다. -> Interface가 많아지고, Presenter의 기능이 중복될 수 있다.), <br/>
View holds the reference to its Presenter and Presenter is aware of its View.<br/>
(뷰는 프레젠터의 레퍼런스를 가지고, 프레젠터는 뷰의 존재를 알고 있다.)
* MVVM : <br/>
One-to-Many relationship between BaseViewModel and View.<br/>
(뷰모델과 뷰는 1:다 관계를 가진다. -> 뷰모델은 계속 재활용될 수 있다.), <br/>
BaseViewModel doesn't have any knowledge of it View.<br/>
(뷰모델은 뷰의 존재를 모른다. (뷰는 뷰모델에 대한 레퍼런스를 가진다.))

## 4. 구조
* <code>Model</code>, <code>View</code>, <code>ViewModel</code> (이 외 adapter, network, widget)으로 구성

#### 4-1. Model
* MVP의 Model과 동일하여 Data와 관련된 전반적인 처리를 Model에서 담당한다.

#### 4-2. View
* 이벤트가 발생하면 ViewModel에 전달한다. View는 ViewModel에 옵저버블 변수의 변경에 따라 바인딩된다.

#### 4-3. ViewModel
* ViewModel은 Model을 래핑하고 View에 필요한 옵저버블 데이터를 가진다. ViewModel은 특정 View에 종속되지 않고, 여러 View에서 계속 재사용될 수 있다.

#### 4-4. adapter, network, widget
* adapter : Databinding을 사용함으로서 BindingViewHolder 클래스를 제너릭 클래스로 정의하여 모든 사용자 정의 객체가 바인딩될 수 있도록 정의할 수 있다. ViewHolder 클래스를 하나만 정의하여 사용할 수 있다.

## 5. 처리순서
1. 
