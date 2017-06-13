package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devqt.idea.project.R;


public class Arduino extends Fragment {

    ImageView ivIcon;


    public static final String IMAGE_RESOURCE_ID = "iconResourceID";


    public Arduino() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.arduino, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.imageView);

        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));
        return view;
    }

}