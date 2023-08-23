package group.acan.cfptdigital;

public class ModelClass {

    private int imageProfil;
    private String txtTitre;
    private String txtDesc;
    private String txtMontant;
    private String txtDivider;
    //Constructeur
    public ModelClass(int imageProfil, String txtTitre, String txtDesc, String txtMontant, String txtDivider) {

        this.imageProfil = imageProfil;
        this.txtTitre = txtTitre;
        this.txtDesc = txtDesc;
        this.txtMontant = txtMontant;
        this.txtDivider = txtDivider;
    }
    //Getter
    public int getImageProfil() {
        return imageProfil;
    }

    public String getTxtTitre() {
        return txtTitre;
    }

    public String getTxtDesc() {
        return txtDesc;
    }

    public String getTxtMontant() {
        return txtMontant;
    }

    public String getTxtDivider() {
        return txtDivider;
    }
   //Setter
    public void setImageProfil(int imageProfil) {
        this.imageProfil = imageProfil;
    }

    public void setTxtTitre(String txtTitre) {
        this.txtTitre = txtTitre;
    }

    public void setTxtDesc(String txtDesc) {
        this.txtDesc = txtDesc;
    }

    public void setTxtMontant(String txtMontant) {
        this.txtMontant = txtMontant;
    }

    public void setTxtDivider(String txtDivider) {
        this.txtDivider = txtDivider;
    }
}
