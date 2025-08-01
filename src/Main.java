import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
import Clases.State.HistorialPedido;
import Clases.State.Pedido;
import Clases.State.PedidoMemento;
import Clases.Strategy.*;
import Clases.Builder.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = Sistema.getInstancia();
        sistema.getGestorVendedor().agregarUsuario(new Administrador("Admin", "admin@mail.com"));
        sistema.getGestorVendedor().agregarUsuario(new Comprador("Laura", "laura@mail.com"));
        sistema.getGestorVendedor().agregarUsuario(new Vendedor("Carlos", "carlos@mail.com"));

        /*
        Administrador usuario = new Administrador("Admin", "admin@mail.com");
        sistema.getGestorVendedor().agregarUsuario(usuario);
        */

        boolean salir = false;
        System.out.println("Ingrese su correo electrónico:");
        String email = scanner.nextLine();

        Usuario usuarioLogueado = sistema.getGestorVendedor()
            .obtenerUsuarios()
            .stream()
            .filter(u -> u.getMail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);

        if (usuarioLogueado == null) {
            System.out.println("❌ Usuario no encontrado. Saliendo del sistema.");
            return;
        }

        System.out.println("Bienvenido, " + usuarioLogueado.getNombre() + " (" + usuarioLogueado.getClass().getSimpleName() + ")");


        while (!salir) {
            System.out.println("\n===== MENÚ DEL SISTEMA =====");
            if(usuarioLogueado instanceof Administrador){
                
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar vehículo");
            System.out.println("3. Mostrar vehículos");
            System.out.println("4. Crear pedido");
            System.out.println("5. Mostrar pedidos");
            System.out.println("6. Avanzar estado de pedido");
            System.out.println("7. Mostrar clientes");
            System.out.println("8. Mostrar vendedores");
            System.out.println("9. Mostrar historial de un pedido en específico");
            System.out.println("10. Mostrar historial de pedidos");
            System.out.println("11. Exportar pedidos en txt");
            System.out.println("12. Exportar precios de los vehiculos");
            System.out.println("13. Exportar precios de los vehiculos");
            System.out.println("14. Salir");
            } else if(usuarioLogueado instanceof Comprador){
                System.out.println("1. Agregar cliente");
                System.out.println("2. Agregar vehículo");
                System.out.println("3. Ver vehículos disponibles");
                System.out.println("4. Crear pedido");
                System.out.println("5. Ver mis pedidos");

                System.out.println("14. Salir");
            } else if(usuarioLogueado instanceof Vendedor){
                System.out.println("3. Ver vehículos disponibles");
                System.out.println("13.Salir");
                
            }
            System.out.print("Elige una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("DNI: ");
                    String dni = scanner.nextLine();
                    System.out.print("Email: ");
                    String email2 = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    Cliente cliente = new Cliente(nombre, dni, email2, telefono);
                    sistema.getGestorCliente().agregarCliente(cliente);
                    System.out.println("Cliente agregado con éxito.");
                    break;

                case 2:
                    System.out.println("Tipo de vehículo: 1-Moto 2-Camioneta 3-Auto 4-Camión");
                    int tipo = Integer.parseInt(scanner.nextLine());
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Chasis: ");
                    String chasis = scanner.nextLine();
                    
                    System.out.print("Motor: ");
                    String motor = scanner.nextLine();
                    System.out.print("Precio: ");
                    float precio = Float.parseFloat(scanner.nextLine());

                    Director director = new Director();

                    switch (tipo) {
                        case 1:
                            MotoBuilder motoBuilder = new MotoBuilder();
                            director.setBuilder(motoBuilder);
                            director.construirVehiculoPersonalizado(marca, chasis, motor, (int) precio, modelo);
                            sistema.getGestorVehiculo().agregarVehiculo(motoBuilder.getResultado());
                            break;
                        case 2:
                            CamionetaBuilder camionetaBuilder = new CamionetaBuilder();
                            director.setBuilder(camionetaBuilder);
                            director.construirVehiculoPersonalizado(marca, chasis, motor, (int) precio, modelo);
                            sistema.getGestorVehiculo().agregarVehiculo(camionetaBuilder.getResultado());
                            break;
                        case 3:
                            AutoBuilder autoBuilder = new AutoBuilder();
                            director.setBuilder(autoBuilder);
                            director.construirVehiculoPersonalizado(marca, chasis, motor, (int) precio, modelo);
                            sistema.getGestorVehiculo().agregarVehiculo(autoBuilder.getResultado());
                            break;
                        case 4:
                            CamionBuilder camionBuilder = new CamionBuilder();
                            director.setBuilder(camionBuilder);
                            director.construirVehiculoPersonalizado(marca, chasis, motor, (int) precio, modelo);
                            sistema.getGestorVehiculo().agregarVehiculo(camionBuilder.getResultado());
                            break;
                        default:
                            System.out.println("Tipo no válido.");
                    }

                    System.out.println("Vehículo agregado con éxito.");
                    break;

                case 3:
                    List<Vehiculo> vehiculos = sistema.getGestorVehiculo().obtenerVehiculos();
                    System.out.println("Vehículos disponibles:");
                    for (Vehiculo v : vehiculos) {
                        System.out.println(v.toStringCatalogo());
                    }
                    break;

                case 4:
                    System.out.print("DNI del cliente: ");
                    String dniCliente = scanner.nextLine();
                    Cliente clientePedido = sistema.getGestorCliente().buscarPorDocumento(dniCliente);
                    if (clientePedido == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                    }

                    System.out.print("Nombre del modelo del vehículo: ");
                    String modeloVehiculo = scanner.nextLine();
                    Vehiculo vehiculo = usuarioLogueado.AsignarColor(sistema.getGestorVehiculo().obtenerVehiculos(), modeloVehiculo);
                    if (vehiculo == null) {
                        System.out.println("Vehículo no encontrado.");
                        break;
                    }

                    System.out.println("Seleccione método de pago:");
                    System.out.println("1. Contado");
                    System.out.println("2. Tarjeta de Crédito");
                    System.out.println("3. Transferencia");
                    int tipoPago = Integer.parseInt(scanner.nextLine());
                    MetodoPago metodoPago;

                    switch (tipoPago) {
                        case 1:
                            metodoPago = new Contado();
                            break;
                        case 2:
                            metodoPago = new TarjetaCredito();
                            break;
                        case 3:
                            metodoPago = new Transferencia();
                            break;
                        default:
                            System.out.println("Opción inválida. Se selecciona Contado por defecto.");
                            metodoPago = new Contado();
                    }

                    float monto = vehiculo.CalculoImpuesto(); // ahora toma el precio del vehículo
                    
                    Pedido pedido = new Pedido(clientePedido, vehiculo, monto, usuarioLogueado, metodoPago);

                int opcionAgregado = -1;
                while (opcionAgregado != 1 && opcionAgregado != 2) {
                    System.out.println("¿Desea agregar algo adicional? 1-Sí  2-No");
                    try {
                        opcionAgregado = Integer.parseInt(scanner.nextLine()); // mejor que nextInt para evitar salto de línea

                        switch (opcionAgregado) {
                            case 1:
                                System.out.print("Ingrese lo que desea agregar: ");
                                String agregado = scanner.nextLine();
                                pedido.setconfadicional(agregado);
                                break;
                            case 2:
                                System.out.println("No se agregará nada.");
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida. Ingrese 1 o 2.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Debe ingresar un número válido.");
                    }
                }

                int opcionFactura = -1;
                while (opcionFactura != 1 && opcionFactura != 2) {
                    System.out.println("¿Desea Factura? 1-Sí  2-No");
                    try {
                        opcionFactura = Integer.parseInt(scanner.nextLine()); // mejor que nextInt para evitar salto de línea

                        switch (opcionFactura) {
                            case 1:
                                System.out.print("Ingrese Razon Social: ");
                                String rz = scanner.nextLine();
                                pedido.setRazonSocial(rz);

                                System.out.print("Ingrese Direccion: ");
                                String d = scanner.nextLine();
                                pedido.setDireccion(d);
                                
                                System.out.print("Ingrese CUIT/CUIL: ");
                                String cuit = scanner.nextLine();
                                pedido.setCuitSocial(cuit);
                                
                                break;
                            case 2:
                                System.out.println("No se realiza facutra.");
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida. Ingrese 1 o 2.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Debe ingresar un número válido.");
                    }
                }

                sistema.getGestorPedido().agregarPedido(pedido);
                System.out.println("✅ Pedido creado con éxito.");


                case 5:
                    List<Pedido> pedidos = sistema.getGestorPedido().obtenerPedidos();
                    System.out.println("Pedidos registrados:");
                    for (Pedido p : pedidos) {
                        System.out.println(p.toString());
                    }
                    break;

                case 6:
                    List<Pedido> listaPedidos = sistema.getGestorPedido().obtenerPedidos();
                    for (int i = 0; i < listaPedidos.size(); i++) {
                        System.out.println(i + ". " + listaPedidos.get(i));
                    }
                    System.out.print("Ingrese el número del pedido a avanzar: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    if (index >= 0 && index < listaPedidos.size()) {
                        Pedido p = listaPedidos.get(index);
                        sistema.getGestorPedido().avanzarEstado(p);
                        System.out.println("Nuevo estado: " + p.getEstadoPedido());
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 7:
                    List<Cliente> clientes = sistema.getGestorCliente().obtenerCliente();
                    System.out.println("Lista de clientes:");
                    for (Cliente c : clientes) {
                        System.out.println(c);
                    }
                    break;

                case 8:
                    List<Usuario> vendedores = sistema.getGestorVendedor().obtenerUsuarios();
                    System.out.println("Lista de vendedores:");
                    for (Usuario u : vendedores) {
                        System.out.println(u);
                    }
                    break;
                
                case 9:
                    System.out.println("Ingrese el número del pedido para ver su historial:");
                    int numero = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer

                    Pedido pedido3 = sistema.getGestorPedido().buscarPorNumero(numero);

                    if (pedido3 != null) {
                        System.out.println("Historial del pedido N° " + pedido3.getNumero() + ":");
                        for (PedidoMemento m : pedido3.obtenerHistorial().obtenerTodos()) {
                            System.out.println(" - Estado: " + m.getNombreEstado());
                        }
                    } else {
                        System.out.println("❌ Pedido no encontrado.");
                    }
                    break;
                
               case 10:
                    System.out.println(" Historial completo de pedidos:");
                    for (Map.Entry<Pedido, HistorialPedido> entry : sistema.getGestorPedido().obtenerHistoriales().entrySet()) {
                        Pedido pedido2 = entry.getKey();
                        HistorialPedido historial = entry.getValue();

                        System.out.println("🔹 Pedido N° " + pedido2.getNumero());
                        for (PedidoMemento m : historial.obtenerTodos()) {
                            System.out.println("   - Estado: " + m.getNombreEstado());
                        }
                    }
                    break;

                case 11:
                    System.out.println(" Generar reporte de pedidos");
                    System.out.print("Ingrese fecha desde (AAAA-MM-DD): ");
                    LocalDate desde = LocalDate.parse(scanner.nextLine());

                    System.out.print("Ingrese fecha hasta (AAAA-MM-DD): ");
                    LocalDate hasta = LocalDate.parse(scanner.nextLine());

                    System.out.print("Ingrese estado a filtrar (o presione Enter para omitir): ");
                    String estado = scanner.nextLine();
                    if (estado.isBlank()) {
                        estado = null;
                    }

                    List<Pedido> reporte = sistema.getGestorPedido().generarReporte(desde, hasta, estado);

                    if (reporte.isEmpty()) {
                        System.out.println(" No se encontraron pedidos con los filtros indicados.");
                    } else {
                        System.out.println("Pedidos encontrados:");
                        for (Pedido p : reporte) {
                            System.out.println(p.toString());
                        }
                    }

                    // OPCIONAL: exportar
                    System.out.print("¿Desea exportar el reporte a archivo? 1-Sí 2-No: ");
                    int opcionExportar = Integer.parseInt(scanner.nextLine());
                    if (opcionExportar == 1) {
                        try (PrintWriter writer = new PrintWriter("reporte_pedidos.txt")) {
                            for (Pedido p : reporte) {
                                writer.println(p.toString());
                                writer.println("--------------------------------------------------");
                            }
                            System.out.println("📁 Reporte exportado a 'reporte_pedidos.txt'");
                        } catch (Exception e) {
                            System.out.println("⚠ Error al guardar el archivo: " + e.getMessage());
                        }
                    }
                    break;

                case 12:
                    sistema.getGestorVehiculo().generarInformeVehiculos("informe_vehiculos.csv");
                    break;
                case 13:
                    
                    List<Pedido> listaPedidos2 = sistema.getGestorPedido().obtenerPedidos();
                    for (int i = 0; i < listaPedidos2.size(); i++) {
                        System.out.println(i + ". " + listaPedidos2.get(i));
                    }
                    System.out.print("Ingrese el número del pedido a avanzar: ");
                    int index2 = Integer.parseInt(scanner.nextLine());
                    if (index2 >= 0 && index2 < listaPedidos2.size()) {
                        Pedido p = listaPedidos2.get(index2);
                        sistema.getGestorPedido().cancelarPedido(p);
                        System.out.println("Nuevo estado: " + p.getEstadoPedido());
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                    
                case 14:
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
