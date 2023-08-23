package group.acan.cfptdigital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;

    public Adapter (List<ModelClass>userList){ this .userList=userList;}

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
       int pp = userList.get(position).getImageProfil();
       String titre = userList.get(position).getTxtTitre();
       String des = userList.get(position).getTxtDesc();
       String prix = userList.get(position).getTxtMontant();
       String divider = userList.get(position).getTxtDivider();

       holder.setData(pp,titre,des,prix,divider);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView imgPP;
        private  TextView txtTITRE;
        private  TextView txtDESC;
        private  TextView txtPRIX;
        private  TextView txtDIVIDER;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPP = itemView.findViewById(R.id.imageview1);
            txtTITRE = itemView.findViewById(R.id.txtTitre);
            txtDESC = itemView.findViewById(R.id.txtDesc);
            txtPRIX = itemView.findViewById(R.id.txtMontant);
            txtDIVIDER = itemView.findViewById(R.id.txtDiv);
        }

        public void setData(int pp, String titre, String des, String prix, String divider) {
            imgPP.setImageResource(pp);
            txtTITRE.setText(titre);
            txtDESC.setText(des);
            txtPRIX.setText(prix);
            txtDIVIDER.setText(divider);
        }
    }
}
