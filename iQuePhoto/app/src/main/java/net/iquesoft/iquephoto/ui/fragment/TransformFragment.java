package net.iquesoft.iquephoto.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.iquesoft.iquephoto.R;
import net.iquesoft.iquephoto.common.BaseFragment;
import net.iquesoft.iquephoto.di.components.IEditorActivityComponent;
import net.iquesoft.iquephoto.presentation.presenter.editor.tools.TransformFragmentPresenterImpl;
import net.iquesoft.iquephoto.presentation.view.editor.EditorView;
import net.iquesoft.iquephoto.presentation.view.editor.tools.TransformView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static net.iquesoft.iquephoto.core.EditorCommand.TRANSFORM_HORIZONTAL;
import static net.iquesoft.iquephoto.core.EditorCommand.TRANSFORM_STRAIGHTEN;
import static net.iquesoft.iquephoto.core.EditorCommand.TRANSFORM_VERTICAL;

public class TransformFragment extends BaseFragment implements TransformView {

    private Unbinder mUnbinder;

    @Inject
    EditorView editorActivityView;

    @Inject
    TransformFragmentPresenterImpl presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IEditorActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transform, container, false);

        mUnbinder = ButterKnife.bind(this, v);

        return v;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.transformBackButton)
    void onClickBack() {
        editorActivityView.navigateBack(true);
    }

    @OnClick(R.id.transformHorizontalButton)
    void onClickTransformHorizontal() {
        editorActivityView.setupFragment(SliderControlFragment.newInstance(TRANSFORM_HORIZONTAL));
    }

    @OnClick(R.id.transformStraightenButton)
    void onClickTransformStraighten() {
        editorActivityView.setupFragment(SliderControlFragment.newInstance(TRANSFORM_STRAIGHTEN));
    }

    @OnClick(R.id.transformVerticalButton)
    void onClickTransformVertical() {
        editorActivityView.setupFragment(SliderControlFragment.newInstance(TRANSFORM_VERTICAL));
    }
}