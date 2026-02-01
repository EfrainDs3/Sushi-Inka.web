package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Venta;
import web.Sushi_Inka.repository.VentaRepository;
import web.Sushi_Inka.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Override
	public List<Venta> obtenerTodos() {
		return ventaRepository.findAll();
	}

	@Override
	public Venta obtenerPorId(Integer id) {
		return ventaRepository.findById(id).orElse(null);
	}

	@Override
	public Venta guardar(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public Venta actualizar(Integer id, Venta venta) {
		Venta ventaExistente = ventaRepository.findById(id).orElse(null);
		if (ventaExistente != null) {
			ventaExistente.setIdSucursal(venta.getIdSucursal());
			ventaExistente.setIdPedido(venta.getIdPedido());
			ventaExistente.setIdUsuario(venta.getIdUsuario());
			ventaExistente.setTotalVenta(venta.getTotalVenta());
			ventaExistente.setTipoComprobante(venta.getTipoComprobante());
			ventaExistente.setNumeroComprobante(venta.getNumeroComprobante());
			ventaExistente.setFechaVenta(venta.getFechaVenta());
			ventaExistente.setEstado(venta.getEstado());
			return ventaRepository.save(ventaExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		ventaRepository.deleteById(id);
	}

	@Override
	public List<Venta> obtenerPorSucursal(Integer idSucursal) {
		return ventaRepository.findByIdSucursal(idSucursal);
	}

	@Override
	public List<Venta> obtenerPorPedido(Integer idPedido) {
		return ventaRepository.findByIdPedido(idPedido);
	}

}
