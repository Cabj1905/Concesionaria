import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Clases.Cliente;
import Clases.Sistema;
import Clases.Gestores.GestorCliente;
import Clases.Gestores.GestorPedido;
import Clases.Gestores.GestorVehiculo;
import Clases.Gestores.GestorVendedor;
import Clases.Personas.Administrador;
import Clases.Personas.Comprador;
import Clases.Personas.Usuario;
import Clases.Personas.Vendedor;
import Clases.State.Pedido;
import Clases.Strategy.*;
import Clases.Builder.*;

public class SistemaConcesionariaTest {
    
    private Sistema sistema;
    private Usuario administrador;
    private Usuario comprador;
    private Usuario vendedor;
    private Cliente clienteTest;
    
    @BeforeEach
    void setUp() {
        // Reiniciar el sistema antes de cada test
        sistema = Sistema.getInstancia();
        
        // Limpiar datos previos si es necesario
        sistema.getGestorCliente().obtenerCliente().clear();
        sistema.getGestorVehiculo().obtenerVehiculos().clear();
        sistema.getGestorPedido().obtenerPedidos().clear();
        sistema.getGestorVendedor().obtenerUsuarios().clear();
        
        // Configurar usuarios de prueba
        administrador = new Administrador("Admin Test", "admin@test.com");
        comprador = new Comprador("Comprador Test", "comprador@test.com");
        vendedor = new Vendedor("Vendedor Test", "vendedor@test.com");
        
        sistema.getGestorVendedor().agregarUsuario(administrador);
        sistema.getGestorVendedor().agregarUsuario(comprador);
        sistema.getGestorVendedor().agregarUsuario(vendedor);
        
        // Cliente de prueba
        clienteTest = new Cliente("Juan Pérez", "12345678", "juan@test.com", "1234567890");
    }
    
    @Test
    @DisplayName("Test Caso 1: Agregar Cliente")
    void testAgregarCliente() {
        // Arrange
        String nombre = "María González";
        String dni = "87654321";
        String email = "maria@test.com";
        String telefono = "0987654321";
        
        // Act
        Cliente cliente = new Cliente(nombre, dni, email, telefono);
        sistema.getGestorCliente().agregarCliente(cliente);
        
        // Assert
        List<Cliente> clientes = sistema.getGestorCliente().obtenerCliente();
        assertEquals(1, clientes.size());
        assertEquals(nombre, clientes.get(0).getNombre());
        assertEquals(dni, clientes.get(0).getDocumento());
        assertEquals(email, clientes.get(0).getEmail());
        assertEquals(telefono, clientes.get(0).getTelefono());
    }
    
    @Test
    @DisplayName("Test Caso 2: Agregar Vehículo - Moto")
    void testAgregarVehiculoMoto() {
        // Arrange
        Director director = new Director();
        MotoBuilder motoBuilder = new MotoBuilder();
        String marca = "Honda";
        String chasis = "CH001";
        String motor = "150cc";
        int precio = 5000;
        String modelo = "CBR150";
        
        // Act
        director.setBuilder(motoBuilder);
        director.construirVehiculoPersonalizado(marca, chasis, motor, precio, modelo);
        Vehiculo moto = motoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(moto);
        
        // Assert
        List<Vehiculo> vehiculos = sistema.getGestorVehiculo().obtenerVehiculos();
        assertEquals(1, vehiculos.size());
        assertTrue(vehiculos.get(0) instanceof Moto);
        assertEquals(marca, vehiculos.get(0).getMarca());
        assertEquals(modelo, vehiculos.get(0).getModelo());
    }
    
    @Test
    @DisplayName("Test Caso 2: Agregar Vehículo - Auto")
    void testAgregarVehiculoAuto() {
        // Arrange
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        String marca = "Toyota";
        String chasis = "CH002";
        String motor = "2.0L";
        int precio = 25000;
        String modelo = "Corolla";
        
        // Act
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado(marca, chasis, motor, precio, modelo);
        Vehiculo auto = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(auto);
        
        // Assert
        List<Vehiculo> vehiculos = sistema.getGestorVehiculo().obtenerVehiculos();
        assertEquals(1, vehiculos.size());
        assertTrue(vehiculos.get(0) instanceof Auto);
        assertEquals(marca, vehiculos.get(0).getMarca());
        assertEquals(modelo, vehiculos.get(0).getModelo());
    }
    
    @Test
    @DisplayName("Test Caso 3: Mostrar Vehículos")
    void testMostrarVehiculos() {
        // Arrange - Agregar varios vehículos
        Director director = new Director();
        
        // Agregar moto
        MotoBuilder motoBuilder = new MotoBuilder();
        director.setBuilder(motoBuilder);
        director.construirVehiculoPersonalizado("Honda", "CH001", "150cc", 5000, "CBR150");
        sistema.getGestorVehiculo().agregarVehiculo(motoBuilder.getResultado());
        
        // Agregar auto
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Toyota", "CH002", "2.0L", 25000, "Corolla");
        sistema.getGestorVehiculo().agregarVehiculo(autoBuilder.getResultado());
        
        // Act
        List<Vehiculo> vehiculos = sistema.getGestorVehiculo().obtenerVehiculos();
        
        // Assert
        assertEquals(2, vehiculos.size());
        assertNotNull(vehiculos.get(0).toStringCatalogo());
        assertNotNull(vehiculos.get(1).toStringCatalogo());
    }
    
    @Test
    @DisplayName("Test Caso 4: Crear Pedido con Método de Pago Contado")
    void testCrearPedidoContado() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Ford", "CH003", "1.6L", 20000, "Focus");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        MetodoPago metodoPago = new Contado();
        float monto = vehiculo.CalculoImpuesto();
        
        // Act
        Pedido pedido = new Pedido(clienteTest, vehiculo, monto, administrador, metodoPago);
        sistema.getGestorPedido().agregarPedido(pedido);
        
        // Assert
        List<Pedido> pedidos = sistema.getGestorPedido().obtenerPedidos();
        assertEquals(1, pedidos.size());
        assertEquals(clienteTest, pedidos.get(0).getCliente());
        assertEquals(vehiculo, pedidos.get(0).getVehiculo());
        assertTrue(pedidos.get(0).getMetodoPago() instanceof Contado);
    }
    
    @Test
    @DisplayName("Test Caso 4: Crear Pedido con Método de Pago Tarjeta de Crédito")
    void testCrearPedidoTarjetaCredito() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Chevrolet", "CH004", "1.8L", 22000, "Cruze");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        MetodoPago metodoPago = new TarjetaCredito();
        float monto = vehiculo.CalculoImpuesto();
        
        // Act
        Pedido pedido = new Pedido(clienteTest, vehiculo, monto, comprador, metodoPago);
        sistema.getGestorPedido().agregarPedido(pedido);
        
        // Assert
        List<Pedido> pedidos = sistema.getGestorPedido().obtenerPedidos();
        assertEquals(1, pedidos.size());
        assertTrue(pedidos.get(0).getMetodoPago() instanceof TarjetaCredito);
    }
    
    @Test
    @DisplayName("Test Caso 5: Mostrar Pedidos")
    void testMostrarPedidos() {
        // Arrange - Crear varios pedidos
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Nissan", "CH005", "2.5L", 28000, "Altima");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        // Crear dos pedidos
        Pedido pedido1 = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), administrador, new Contado());
        Pedido pedido2 = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), comprador, new Transferencia());
        
        sistema.getGestorPedido().agregarPedido(pedido1);
        sistema.getGestorPedido().agregarPedido(pedido2);
        
        // Act
        List<Pedido> pedidos = sistema.getGestorPedido().obtenerPedidos();
        
        // Assert
        assertEquals(2, pedidos.size());
        assertNotNull(pedidos.get(0).toString());
        assertNotNull(pedidos.get(1).toString());
    }
    
    @Test
    @DisplayName("Test Caso 6: Avanzar Estado de Pedido")
    void testAvanzarEstadoPedido() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("BMW", "CH006", "3.0L", 45000, "320i");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        Pedido pedido = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), administrador, new Contado());
        sistema.getGestorPedido().agregarPedido(pedido);
        
        String estadoInicial = pedido.getEstadoPedido();
        
        // Act
        sistema.getGestorPedido().avanzarEstado(pedido);
        String estadoFinal = pedido.getEstadoPedido();
        
        // Assert
        assertNotEquals(estadoInicial, estadoFinal);
        assertNotNull(estadoFinal);
    }
    
    @Test
    @DisplayName("Test Caso 7: Mostrar Clientes")
    void testMostrarClientes() {
        // Arrange
        Cliente cliente1 = new Cliente("Ana López", "11111111", "ana@test.com", "1111111111");
        Cliente cliente2 = new Cliente("Carlos Ruiz", "22222222", "carlos@test.com", "2222222222");
        
        sistema.getGestorCliente().agregarCliente(cliente1);
        sistema.getGestorCliente().agregarCliente(cliente2);
        
        // Act
        List<Cliente> clientes = sistema.getGestorCliente().obtenerCliente();
        
        // Assert
        assertEquals(2, clientes.size());
        assertTrue(clientes.contains(cliente1));
        assertTrue(clientes.contains(cliente2));
    }
    
    @Test
    @DisplayName("Test Caso 8: Mostrar Vendedores")
    void testMostrarVendedores() {
        // Act
        List<Usuario> usuarios = sistema.getGestorVendedor().obtenerUsuarios();
        
        // Assert
        assertEquals(3, usuarios.size()); // Los 3 usuarios creados en setUp
        assertTrue(usuarios.stream().anyMatch(u -> u instanceof Administrador));
        assertTrue(usuarios.stream().anyMatch(u -> u instanceof Comprador));
        assertTrue(usuarios.stream().anyMatch(u -> u instanceof Vendedor));
    }
    
    @Test
    @DisplayName("Test Caso 9: Buscar Pedido por Número")
    void testBuscarPedidoPorNumero() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Audi", "CH007", "2.0L", 40000, "A4");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        Pedido pedido = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), administrador, new Contado());
        sistema.getGestorPedido().agregarPedido(pedido);
        
        int numeroPedido = pedido.getNumero();
        
        // Act
        Pedido pedidoEncontrado = sistema.getGestorPedido().buscarPorNumero(numeroPedido);
        
        // Assert
        assertNotNull(pedidoEncontrado);
        assertEquals(numeroPedido, pedidoEncontrado.getNumero());
        assertEquals(pedido, pedidoEncontrado);
    }
    
    @Test
    @DisplayName("Test Caso 11: Generar Reporte de Pedidos por Fecha")
    void testGenerarReportePedidos() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Mercedes", "CH008", "2.2L", 50000, "C200");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        Pedido pedido = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), administrador, new Contado());
        sistema.getGestorPedido().agregarPedido(pedido);
        
        LocalDate desde = LocalDate.now().minusDays(1);
        LocalDate hasta = LocalDate.now().plusDays(1);
        
        // Act
        List<Pedido> reporte = sistema.getGestorPedido().generarReporte(desde, hasta, null);
        
        // Assert
        assertFalse(reporte.isEmpty());
        assertTrue(reporte.contains(pedido));
    }
    
    @Test
    @DisplayName("Test Caso 12: Generar Informe de Vehículos")
    void testGenerarInformeVehiculos() {
        // Arrange
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Volkswagen", "CH009", "1.4L", 18000, "Golf");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        String nombreArchivo = "test_informe_vehiculos.csv";
        
        // Act
        sistema.getGestorVehiculo().generarInformeVehiculos(nombreArchivo);
        
        // Assert
        File archivo = new File(nombreArchivo);
        assertTrue(archivo.exists());
        
        // Cleanup
        archivo.delete();
    }
    
    @Test
    @DisplayName("Test Caso 13: Cancelar Pedido")
    void testCancelarPedido() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        Director director = new Director();
        AutoBuilder autoBuilder = new AutoBuilder();
        director.setBuilder(autoBuilder);
        director.construirVehiculoPersonalizado("Hyundai", "CH010", "1.6L", 16000, "Elantra");
        Vehiculo vehiculo = autoBuilder.getResultado();
        sistema.getGestorVehiculo().agregarVehiculo(vehiculo);
        
        Pedido pedido = new Pedido(clienteTest, vehiculo, vehiculo.CalculoImpuesto(), administrador, new Contado());
        sistema.getGestorPedido().agregarPedido(pedido);
        
        String estadoInicial = pedido.getEstadoPedido();
        
        // Act
        sistema.getGestorPedido().cancelarPedido(pedido);
        String estadoFinal = pedido.getEstadoPedido();
        
        // Assert
        assertNotEquals(estadoInicial, estadoFinal);
        // Asumiendo que el estado cancelado tiene un nombre específico
        assertTrue(estadoFinal.toLowerCase().contains("cancelado") || 
                  estadoFinal.toLowerCase().contains("cancel"));
    }
    
    @Test
    @DisplayName("Test Buscar Cliente por Documento")
    void testBuscarClientePorDocumento() {
        // Arrange
        sistema.getGestorCliente().agregarCliente(clienteTest);
        
        // Act
        Cliente clienteEncontrado = sistema.getGestorCliente().buscarPorDocumento("12345678");
        Cliente clienteNoEncontrado = sistema.getGestorCliente().buscarPorDocumento("99999999");
        
        // Assert
        assertNotNull(clienteEncontrado);
        assertEquals(clienteTest, clienteEncontrado);
        assertNull(clienteNoEncontrado);
    }
    
    @Test
    @DisplayName("Test Login de Usuario")
    void testLoginUsuario() {
        // Act
        Usuario usuarioEncontrado = sistema.getGestorVendedor()
            .obtenerUsuarios()
            .stream()
            .filter(u -> u.getMail().equalsIgnoreCase("admin@test.com"))
            .findFirst()
            .orElse(null);
        
        Usuario usuarioNoEncontrado = sistema.getGestorVendedor()
            .obtenerUsuarios()
            .stream()
            .filter(u -> u.getMail().equalsIgnoreCase("noexiste@test.com"))
            .findFirst()
            .orElse(null);
        
        // Assert
        assertNotNull(usuarioEncontrado);
        assertEquals(administrador, usuarioEncontrado);
        assertNull(usuarioNoEncontrado);
    }
}
