package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unam.jigm.nasa2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mario on 31/08/2016.
 */
public class fragment_acecade extends Fragment {
    @BindView(R.id.textView)
    TextView Title;
    @BindView(R.id.textView2)
    TextView Nombre;
    @BindView(R.id.textView3)
    TextView Supervisa;
    @BindView(R.id.textView4)
    TextView Explica;
    @BindView(R.id.textView5)
    TextView Hoy;
    @BindView(R.id.textView6)
    TextView Diploma;
    @BindView(R.id.textView7)
    TextView favorito;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sobre_aplicacion,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
