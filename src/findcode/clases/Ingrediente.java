package findcode.clases;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ingrediente {
    
    int iD;
    String titulo;
    String descripcion;
    int posInicial;
    int posFinal;
    int iDFicha;

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPosInicial() {
        return posInicial;
    }

    public void setPosInicial(int posInicial) {
        this.posInicial = posInicial;
    }

    public int getPosFinal() {
        return posFinal;
    }

    public void setPosFinal(int posFinal) {
        this.posFinal = posFinal;
    }

    public int getiDFicha() {
        return iDFicha;
    }

    public void setiDFicha(int iDFicha) {
        this.iDFicha = iDFicha;
    }
    
    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TIngrediente (titulo, descripcion, posInicial, posFinal, iDFicha)"
                    + " values (?, ?, ?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setInt(3, posInicial);
            db.getSentencia().setInt(4, posFinal);
            db.getSentencia().setInt(5, iDFicha);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TIngrediente where"
                    + " iD = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                
                titulo = db.getResultados().getString("titulo");
                descripcion = db.getResultados().getString("descripcion");
                posInicial = db.getResultados().getInt("posInicial");
                posFinal = db.getResultados().getInt("posFinal");
                iDFicha = db.getResultados().getInt("iDFicha");
                
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public ArrayList<Ingrediente> cargarPorFicha(int iDFicha) {

        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        
        try {

            MySQL db = new MySQL();

            String query = " select * from TIngrediente where"
                    + " iDFicha = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iDFicha);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {
                
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setTitulo(db.getResultados().getString("titulo"));
                ingrediente.setDescripcion(db.getResultados().getString("descripcion"));
                ingrediente.setPosInicial(db.getResultados().getInt("posInicial"));
                ingrediente.setPosFinal(db.getResultados().getInt("posFinal"));
                ingrediente.setiDFicha(db.getResultados().getInt("iDFicha"));
                
                ingredientes.add(ingrediente);

            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ingredientes;

    }

    public void editar() {

        try {

            MySQL db = new MySQL();

            String query = "UPDATE TIngrediente "
                    + "SET titulo = ?, "
                    + "descripcion = ?, "
                    + "posInicial = ?, "
                    + "posFinal = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setInt(3, posInicial);
            db.getSentencia().setInt(4, posFinal);
            db.getSentencia().setInt(5, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TIngrediente "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
