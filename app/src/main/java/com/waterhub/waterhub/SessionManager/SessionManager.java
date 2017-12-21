package com.waterhub.waterhub.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.waterhub.waterhub.model.User;


/**
 * Created by Nadian on 11/26/2017.
 */

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "nama_pref";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        //preferences editor
        editor = pref.edit();
    }

    public void saveData(String message){
        editor.putString("message", message);
        editor.commit();
    }

    public String getData(){
        return pref.getString("message","");
    }

    public void saveUser(User user) {
        saveUser(user.getEmail());
    }

    public void saveUser(String email) {
        editor.putString("user", email);
        editor.commit();
    }

    public String getUserEmail() {
        return pref.getString("user", null);
    }

    public Boolean isLogin() {
        String result = getUserEmail();
        return result != null;
    }

    public void deleteUser() {
        editor.remove("user");
        editor.commit();
    }

    //method untuk menyimpan data
//    public void setLoginData(String nama,String alamat,String no){
//        editor.putString("namauser",nama);
//        editor.putString("alamat",alamat);
//        editor.putString("notelpon",no);
//        editor.commit();
//    }
//
//    public String getNama(){
//        String uid = pref.getString("namauser","");
//        return uid;
//    }
//    public String getalamat(){
//        String uid = pref.getString("alamat","");
//        return uid;
//    }
//    public String getNotelpon(){
//        String uid = pref.getString("notelpon","");
//        return uid;
//    }
//
//    public void setPulsa(String pulsa){
//        editor.putString("data",pulsa);
//        editor.commit();
//    }
//    public String getPulsa(){
//        String pulsa = pref.getString("data","");
//        return pulsa;
//    }
//
//
//    public void setNotransNTRx(String notrans,String Trx,String uid){
//        editor.putString("uidpayment",uid);
//        editor.putString("notrans",notrans);
//        editor.putString("Trx",Trx);
//        editor.commit();
//    }
//    public String getuidPayment(){
//        String uid = pref.getString("uidpayment","");
//        return uid;
//    }
//    public String getNotrans(){
//        String notrans = pref.getString("notrans","");
//        return notrans;
//    }
//    public String getTrx(){
//        String notrans = pref.getString("Trx","");
//        return notrans;
//    }
//    public void setId(String id){
//        editor.putString("uid",id);
//        editor.commit();
//    }
//    public void setprovider(String  provider){
//        editor.putString("provider",provider);
//        editor.commit();
//    }
//    public String  getProvider(){
//        String  provider = pref.getString("provider","");
//        return provider;
//    }
//
//    public String getid(){
//        String id = pref.getString("uid","");
//        return id;
//    }
//    public void setAutorization(String Aut){
//        editor.putString("Aut",Aut);
//        editor.commit();
//    }
//    public String getAut(){
//        String AUt = pref.getString("Aut","");
//        return AUt;
//    }
//    public void setTiketCategori(int anak,int dewasa){
//        editor.putInt("anak",anak);
//        editor.putInt("dewasa",dewasa);
//        editor.commit();
//    }
//
//    public void setSaldo(long saldo){
//        editor.putLong("saldo",saldo);
//        editor.commit();
//    }
//
//    public long getSaldo(){
//        long  saldo = pref.getLong("saldo",0);
//        return saldo;
//    }
//    public int getanak(){
//        int no = pref.getInt("anak",0);
//        return no;
//    }
//    public int getdewasa(){
//        int no = pref.getInt("dewasa",0);
//        return no;
//    }
//    public void setString(String siap){
//        editor.putString("no",siap);
//        editor.commit();
//    }
//    public String ok(){
//        String nama = pref.getString("no","");
//        return nama;
//    }
//    public void setInt(int no){
//        editor.putInt("beda",no);
//        editor.commit();
//    }
//    public int getInt(){
//        int no = pref.getInt("beda",0);
//        return no;
//    }
//    public void SetTransaksi(String nama,int harga,int gambar,int charger){
//        editor.putString("nami",nama);
//        editor.putInt("harga",harga);
//        editor.putInt("gambar",gambar);
//        editor.putInt("charger",charger);
//        editor.commit();
//    }
//
//    public int getCharger(){
//        int charger = pref.getInt("charger",0);
//        return charger;
//    }
//    public String getnama(){
//        String nami = pref.getString("nami","");
//        return nami;
//    }
//    public int hetharga(){
//        int harga = pref.getInt("harga",0);
//        return harga;
//    }
//    public int getgambar(){
//        int gambar = pref.getInt("gambar",0);
//        return gambar;
//    }
//
//    public void SetVersion(Boolean versi){
//        editor.putBoolean("versi",versi);
//        editor.commit();
//    }
//
//    public boolean getVersion(){
//        boolean gambar = pref.getBoolean("versi",false);
//        return gambar;
//    }
//
//    public void SetLinkVersion(String  versi){
//        editor.putString("link",versi);
//        editor.commit();
//    }
//
//    public String getLinkVersion(){
//        String gambar = pref.getString("link","");
//        return gambar;
//    }
//
//    public void setLogin(boolean isLoggedIn) {
//
//        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
//
//        // commit changes
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//    public boolean isLoggedIn(){
//        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
//    }
}
