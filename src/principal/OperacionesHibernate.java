/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import beans.Cita;
import beans.CitaDate;
import beans.Cliente;
import beans.HistorialClinico;
import beans.Hora;
import beans.Mascota;
import beans.Tratamiento;
import beans.Veterinario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Alex
 */
//Clase para ralizar todo el trabajo de base de datos con hibernate
public class OperacionesHibernate {

    public OperacionesHibernate() {
    }

    //dar de alta un cliente
    public static void altaCliente(Cliente c) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();

        session.close();
    }

    //dar de alta una linea de historial
    public static void altaHistorial(HistorialClinico hc) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(hc);
        session.getTransaction().commit();

        session.close();
    }

    //devolver id mascota de un cliente determinado
    public static int devuelveIdMascota(String nombreMascota, int idcliente) {
        int idmascota = 0;

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT m FROM Mascota m");
        List<Mascota> mascotas = query.list();

        for (int i = 0; i < mascotas.size(); i++) {
            if (mascotas.get(i).getNombre().toLowerCase().equals(nombreMascota.toLowerCase()) && mascotas.get(i).getIdcliente() == idcliente) {
                idmascota = mascotas.get(i).getIdmascota();
            }
        }

        session.close();

        return idmascota;
    }

    //Método para modificar una cita para app android
    public static void modificarCita(CitaDate c, int idcita) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Cita cita = (Cita) session.get(Cita.class, idcita);

        cita.setMotivo(c.getMotivo());
        cita.setFecha(new Date(c.getFecha()));
        cita.setIdcliente(c.getIdcliente());
        cita.setIdmascota(c.getIdmascota());

        session.beginTransaction();
        session.update(cita);
        session.getTransaction().commit();

        session.close();
    }

    //modificar una fecha de una cita para app escritorio
    public static void modificarFechaCita(String idcita, String fecha) {

        int id = Integer.parseInt(idcita);
        Date date = new Date(Long.parseLong(fecha));

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Cita cita = (Cita) session.get(Cita.class, id);

        cita.setFecha(date);

        session.beginTransaction();
        session.update(cita);
        session.getTransaction().commit();

        session.close();
    }

    //modificar un cliente 
    public static void modificarCliente(Cliente c) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Cliente cliente = (Cliente) session.get(Cliente.class, c.getIdcliente());

        cliente.setNombre(c.getNombre());
        cliente.setApellidos(c.getApellidos());
        cliente.setDni(c.getDni());
        cliente.setDireccion(c.getDireccion());
        cliente.setPoblacion(c.getPoblacion());
        cliente.setTelefono(c.getTelefono());
        cliente.setEmail(c.getEmail());
        cliente.setBaja(c.getBaja());

        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();

        session.close();
    }

    //dar de alta una cita
    public static int altaCita(CitaDate c) {

        Cita cita = new Cita(c.getMotivo(), new Date(c.getFecha()), c.getIdcliente(), c.getIdmascota());

        if (existeCita(cita)) {
            return 1;
        } else {

            SessionFactory sessionFactory;

            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(cita);
            session.getTransaction().commit();

            session.close();

            return 0;
        }

    }

    //Comprobar si existe una cita o no
    public static boolean existeCita(Cita cita) {
        boolean existe = false;

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cita c WHERE idmascota=" + cita.getIdmascota());
        List<Cita> citas = query.list();

        if (citas.size() > 0) {
            for (int i = 0; i < citas.size(); i++) {
                if (citas.get(i).getFecha().after(new Date())) {
                    existe = true;
                    break;
                } else {
                    existe = false;
                }
            }
        }

        session.close();

        return existe;
    }

    //Devolver una lista del historial de una mascota
    public static List<HistorialClinico> listaHistorialPorMascota(int idmascota) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT h FROM HistorialClinico h WHERE idmascota=" + idmascota);
        List<HistorialClinico> historial = query.list();

        session.close();

        return historial;

    }

    //dar de alta una mascota
    public static void altaMascota(Mascota m) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();

        session.close();
    }

    //eliminar una mascota
    public static void bajaMascota(int idmascota) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT m FROM Mascota m WHERE idmascota=" + idmascota);
        List<Mascota> mascotas = query.list();

        session.close();

        configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();

        session.beginTransaction();
        session.delete(mascotas.get(0));
        session.getTransaction().commit();

        session.close();
    }

    //eliminar historial
    public static void eliminarHistorial(int idmascota) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT h FROM HistorialClinico h WHERE idmascota=" + idmascota);
        List<HistorialClinico> historial = query.list();

        session.close();

        configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();

        for (int i = 0; i < historial.size(); i++) {
            session.beginTransaction();
            session.delete(historial.get(i));
            session.getTransaction().commit();
        }

        session.close();
    }

    //dar de baja a un cliente
    public static void bajaCliente(String idcliente, String fecha) {
        int id = Integer.parseInt(idcliente);
        Date date = new Date(Long.parseLong(fecha));

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Cliente c = (Cliente) session.get(Cliente.class, id);

        c.setBaja(date);

        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();

        session.close();
    }

    //busca usuario por nombre, devulve el id si existe y -1 si no existe
    public static boolean busquedaVeterinario(int id, String clave) {
        boolean existe = false;
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT v FROM Veterinario v");
        List<Veterinario> veterinarios = query.list();
        for (int i = 0; i < veterinarios.size(); i++) {

            if (veterinarios.get(i).getIdveterinario() == id && veterinarios.get(i).getClave().equals(clave)) {
                existe = true;
                break;
            }
        }
        session.close();
        return existe;
    }

    //comprueba si existe ese id de veterinario con esa clave y modifica la clave por la nueva
    public static boolean busquedaVeterinario(int id, String clave, String nuevaClave) {
        boolean existe = false;
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT v FROM Veterinario v");
        List<Veterinario> veterinarios = query.list();
        for (int i = 0; i < veterinarios.size(); i++) {

            if (veterinarios.get(i).getIdveterinario() == id && veterinarios.get(i).getClave().equals(clave)) {
                existe = true;
                veterinarios.get(i).setClave(nuevaClave);
                session.beginTransaction();
                session.update(veterinarios.get(i));
                session.getTransaction().commit();

                break;
            }
        }
        session.close();
        return existe;
    }

    //busca cliente por id y clave, si existe devuelve true
    public static boolean busquedaCliente(int id, String clave) {
        boolean existe = false;
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.list();
        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getIdcliente() == id && clientes.get(i).getClave().equals(clave)) {
                existe = true;
                break;
            }
        }
        session.close();
        return existe;
    }

   //comprueba si existe ese id cliente con esa clave y modifica la clave por la nueva
    public static boolean busquedaCliente(int id, String clave, String nuevaClave) {
        boolean existe = false;
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.list();
        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getIdcliente() == id && clientes.get(i).getClave().equals(clave)) {
                existe = true;
                clientes.get(i).setClave(nuevaClave);
                session.beginTransaction();
                session.update(clientes.get(i));
                session.getTransaction().commit();

                break;
            }
        }
        session.close();
        return existe;
    }

    //devuelve el id del cliente si existe y si no, devuelve -1
    public static int busquedaCliente(String dniCliente) {
        int idcliente = -1;
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.list();
        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getDni().equals(dniCliente)) {
                idcliente = clientes.get(i).getIdcliente();
                break;
            }
        }
        session.close();
        return idcliente;
    }

    //devuelve una lista de tratamientos
    public static List<String> devuelveTratamiento() {
        List<String> lista = new ArrayList();
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT t FROM Tratamiento t");
        List<Tratamiento> tratamientos = query.list();
        for (int i = 0; i < tratamientos.size(); i++) {
            lista.add(tratamientos.get(i).getIdtratamiento() + "." + tratamientos.get(i).getDescripcion());
        }
        session.close();
        return lista;
    }

    //devuelve una lista de todos los veterinarios
    public static List<String> devuelveVeterinario() {
        List<String> v = new ArrayList();

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT v FROM Veterinario v");
        List<Veterinario> veterinarios = query.list();

        for (int i = 0; i < veterinarios.size(); i++) {
            v.add(veterinarios.get(i).getIdveterinario() + "." + veterinarios.get(i).getApellidos() + ", " + veterinarios.get(i).getNombre() + " (" + veterinarios.get(i).getDni() + ")");
        }

        session.close();
        return v;
    }

    //devulve un veterinario pasandole el dni si es que existe
    public static int devuelveIdVeterinario(String dni) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT v FROM Veterinario v WHERE v.dni like:dni").setParameter("dni", dni);
        List<Veterinario> veterinarios = query.list();

        session.close();

        if (veterinarios.size() > 0) {
            return veterinarios.get(0).getIdveterinario();
        } else {
            return 0;
        }
    }

    //devuelve una lista completa de mascotas
    public static List<Mascota> devuelveMascota() {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT m FROM Mascota m");
        List<Mascota> mascotas = query.list();

        session.close();
        return mascotas;
    }

    //devuelve una o varias mascota de un cliente, pasandole su DNI
    public static List<Mascota> devuelveMascota(String dni) {
        int id = busquedaCliente(dni);

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT m FROM Mascota m WHERE m.idcliente like:idcliente").setParameter("idcliente", id);
        List<Mascota> mascotas = query.list();

        session.close();
        return mascotas;
    }
    
    //devuelve una o varias mascota de un cliente pasando el ID
    public static List<Mascota> devuelveMascota(int id) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT m FROM Mascota m WHERE m.idcliente like:idcliente").setParameter("idcliente", id);
        List<Mascota> mascotas = query.list();

        session.close();
        return mascotas;
    }

    //devuelve una lista completa de clientes
    public static List<Cliente> devuelveCliente() {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c");
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    //devuelve clientes por nombre
    public static List<Cliente> devuelveCliente(String nombre) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c WHERE c.nombre like:nombre").setParameter("nombre", nombre);
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    //devuelve clientes por apellidos
    public static List<Cliente> devuelveClienteApellidos(String apellidos) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c WHERE c.apellidos like:apellidos").setParameter("apellidos", apellidos);
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    //devuelve clientes por poblacion
    public static List<Cliente> devuelveClientePoblacion(String poblacion) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c WHERE c.poblacion like:poblacion").setParameter("poblacion", poblacion);
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    //devuelve clientes por dados de baja
    public static List<Cliente> devuelveClienteBaja() {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cliente c WHERE c.baja is not null");
        List<Cliente> clientes = query.list();

        session.close();
        return clientes;
    }

    //devuelve todas las citas
    public static List<Cita> devuelveCitas() {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cita c");
        List<Cita> citas = query.list();

        session.close();
        return citas;
    }

    //comprueba si existe una fecha para una determinada fecha y hora
    public static boolean comrpuebaCitas(String fecha) {

        Date d = new Date(Long.parseLong(fecha));

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cita c WHERE c.fecha like:fecha").setParameter("fecha", d);
        List<Cita> citas = query.list();

        session.close();

        if (citas.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    //devuelve una lista de citas pasandole el id del cliente
    public static List<Cita> devuelveCitas(int id) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cita c WHERE c.idcliente=" + id);
        List<Cita> citas = query.list();

        session.close();
        return citas;
    }

    //delvuelve la hora pasandole el id de una hora
    public static String devuelveHora(int idhora) {
        String hora = "";

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT h FROM Hora h");
        List<Hora> horas = query.list();

        for (int i = 0; i < horas.size(); i++) {
            if (horas.get(i).getIdhora() == idhora) {
                hora = horas.get(i).getHora();
            }
        }

        session.close();
        return hora;
    }

    //elimina una cita pasandole el id de la cita
    public static void eliminarCita(int idcita) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Cita cita = new Cita();
        cita.setIdcita(idcita);

        session.beginTransaction();
        session.delete(cita);
        session.getTransaction().commit();

        session.close();
    }
    
    //eliminar todas las citas de una mascota que se va a eliminar
    public static void eliminarCitaMascota(int idmascota) {

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("SELECT c FROM Cita c WHERE idmascota=" + idmascota);
        List<Cita> citas = query.list();

        session.close();

        configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();

        for (int i = 0; i < citas.size(); i++) {
            session.beginTransaction();
            session.delete(citas.get(i));
            session.getTransaction().commit();
        }

        session.close();
    }
    
    //poner como pagado una linea del historial
    public static void pagarHistorial(String idhistorial) {
        int id = Integer.parseInt(idhistorial);

        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        HistorialClinico hc = (HistorialClinico) session.get(HistorialClinico.class, id);

        hc.setPagado("Si");

        session.beginTransaction();
        session.update(hc);
        session.getTransaction().commit();

        session.close();
    }
}
