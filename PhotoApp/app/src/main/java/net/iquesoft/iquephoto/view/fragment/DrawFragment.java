package net.iquesoft.iquephoto.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.iquesoft.iquephoto.DataHolder;
import net.iquesoft.iquephoto.R;
import net.iquesoft.iquephoto.common.BaseFragment;
import net.iquesoft.iquephoto.di.components.IMainActivityComponent;
import net.iquesoft.iquephoto.presenter.DrawFragmentPresenterImpl;
import net.iquesoft.iquephoto.view.IDrawFragmentView;
import net.iquesoft.iquephoto.view.dialog.ColorPickerDialog;
import net.iquesoft.iquephoto.view.dialog.RGBColorPickerDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Sergey
 */
public class DrawFragment extends BaseFragment implements IDrawFragmentView {

    private ColorPickerDialog colorPickerDialog;

    @Inject
    DrawFragmentPresenterImpl presenter;

    @BindView(R.id.drawingSettings)
    LinearLayout drawingSettings;

    @BindView(R.id.hideDrawingButton)
    ImageView hideDrawingButton;

    private boolean isHide;

    private Unbinder unbinder;
    private RGBColorPickerDialog RGBColorPickerDialog;

    public static DrawFragment newInstance() {
        /*Bundle b = new Bundle();
        b.putString("msg", text);
        b.putString("color", color);
        f.setArguments(b);*/
        return new DrawFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawing, container, false);
        v.setAlpha(0.8f);

        unbinder = ButterKnife.bind(this, v);

        colorPickerDialog = new ColorPickerDialog(v.getContext());
        colorPickerDialog.setListener(color -> {
            DataHolder.getInstance().getEditorView().setDrawingColor(color);
        });
        RGBColorPickerDialog = new RGBColorPickerDialog(v.getContext());

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.brushColorButton)
    public void onClickBrushColor() {
        colorPickerDialog.show();
    }

    @OnClick(R.id.hideDrawingButton)
    public void onClickHideDrawing() {
        if (!isHide) {
            isHide = true;
            hideDrawingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand_less));
            drawingSettings.setVisibility(View.GONE);
        } else {
            isHide = false;
            hideDrawingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_expand));
            drawingSettings.setVisibility(View.VISIBLE);
        }
    }

}
