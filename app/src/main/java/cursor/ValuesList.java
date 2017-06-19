package cursor;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devqt.idea.project.R;

public class ValuesList {

    TextView txtName;
    ImageView img;


    public ValuesList(View itemView){

        txtName = (TextView) itemView.findViewById(R.id.nameSpace);
        img = (ImageView) itemView.findViewById(R.id.pic);


    }

    }

