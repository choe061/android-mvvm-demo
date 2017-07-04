package com.example.mvvmapp.view;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mvvmapp.R;
import com.example.mvvmapp.adapter.GithubUserRecyclerAdapter;
import com.example.mvvmapp.databinding.ActivityMainBinding;
import com.example.mvvmapp.model.repository.request.UserApiRequest;
import com.example.mvvmapp.viewmodel.user.UserViewModel;
import com.example.mvvmapp.viewmodel.user.UsersViewModel;

import java.util.ArrayList;

/**
 * 기본적인 MVVM Pattern에 대해 학습
 *
 * MVP 패턴에서는 Presenter가 MVC의 Controller처럼 비지니스 로직 코드가 쏠리고, Interface가 많아지는 문제점이 있다.
 * MVP에서는 하나의 View에 하나의 Presenter가 1:1로 대응하기 때문에 동일한 기능을하는 Presenter가 중복 생성될 수 있다.
 *
 * MVVM은 보통 Data-binding과 함께 사용하여 테스트와 모듈화가 쉽고,
 * View와 Model을 연결하기 위해 사용하는 연결 코드를 줄일 수 있다는 장점이 있다.
 *
 * MVP와 MVVM의 공통점
 * View is not aware of the Model. Presenter(or BaseViewModel) update the Model.
 * 뷰는 모델의 존재를 모른다. 프레젠터나 뷰모델이 모델로부터 값을 받아 업데이트 시킨다.
 *
 * MVP와 MVVM의 차이점
 * MVP : One-to-One relationship between View and Presenter.
 *       View holds the reference to its Presenter and Presenter is aware of its View.
 *       뷰와 프레젠터가 1:1 관계를 가진다.
 *       뷰는 프레젠터를 가지고, 프레젠터는 뷰의 존재를 알고 있다.
 * MVVM : One-to-Many relationship between BaseViewModel and View.
 *        BaseViewModel doesn't have any knowledge of it View.
 *        뷰모델과 뷰는 1:다 관계를 가진다. (뷰모델은 계속 재활용될 수 있다.)
 *        뷰모델은 뷰의 존재를 모른다. (뷰는 뷰모델에 대한 참조자를 가진다.)
 *
 *        MVVM은 사용자 이벤트 기반 프로그래밍으로 ViewModel이 이벤트들의 스트림을 View가 그 스트림에 바인드할 수 있도록
 *        노출 시킨다.
 *        View들은 ViewModel에게 다른 액션이 발생했음을 알려준다. 그래서 MVVM은 View와 ViewModel의 양방향 데이터 바인딩을
 *        지원한다.
 *        MVVM패턴에 필요한 이벤트 기반 프로그래밍 부분을 RxJava의 Observable을 사용하여 해결한다.
 *
 * *Google Architecture
 *      1. Model : domain model을 가리키거나, data access layer를 가리킨다.(MVC, MVP와 동일)
 *      2. View : 이벤트가 발생하면 ViewModel에 전달한다.
 *                View는 ViewModel에 의해 보여지는 옵저버블 변수와 액션에 유연하게 바인딩된다.
 *      3. BaseViewModel : ViewModel은 공동의 속성 및 명령을 표시하는 View의 추상화이다.
 *                     binder는 View와 data binder 사이의 통신을 중개한다.
 *                     Model의 data를 묘사한다.
 *
 *                     View에 관련된 데이터 스트림을 드러낸다.
 *                     ViewModel은 Model을 래핑하고 View에 필요한 옵저버블 데이터를 준비한다.
 *                     View가 Model에 이벤트를 전달할 수 있도록 훅(hook)을 준비한다.
 *                     그러면서도 ViewModel이 View에 종속되지는 않는다.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private UsersViewModel usersViewModel;
    @SuppressLint("StaticFieldLeak") private static RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersViewModel = new UsersViewModel(new UserApiRequest());
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(usersViewModel);
        binding.setView(this);
        binding.setListener(position -> {
            Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        });

        requestManager = Glide.with(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usersViewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        usersViewModel.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersViewModel.onDestroy();
    }

    @BindingAdapter({"items"})
    public static void setUsers(RecyclerView recyclerView, ArrayList<UserViewModel> users) {
        GithubUserRecyclerAdapter adapter;
        if (recyclerView.getAdapter() == null) {
            adapter = new GithubUserRecyclerAdapter(requestManager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = (GithubUserRecyclerAdapter) recyclerView.getAdapter();
        }
        adapter.addItems(users);
    }

}
