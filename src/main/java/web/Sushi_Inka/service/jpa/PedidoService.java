package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Pedido;
import web.Sushi_Inka.repository.PedidoRepository;
import web.Sushi_Inka.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<Pedido> obtenerTodos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido obtenerPorId(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	@Override
	public Pedido guardar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido actualizar(Integer id, Pedido pedido) {
		Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
		if (pedidoExistente != null) {
			pedidoExistente.setIdSucursal(pedido.getIdSucursal());
			pedidoExistente.setIdMesa(pedido.getIdMesa());
			pedidoExistente.setIdUsuario(pedido.getIdUsuario());
			pedidoExistente.setTipoPedido(pedido.getTipoPedido());
			pedidoExistente.setEstadoPedido(pedido.getEstadoPedido());
			pedidoExistente.setTotal(pedido.getTotal());
			pedidoExistente.setFechaHora(pedido.getFechaHora());
			return pedidoRepository.save(pedidoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		pedidoRepository.deleteById(id);
	}

	@Override
	public List<Pedido> obtenerPorSucursal(Integer idSucursal) {
		return pedidoRepository.findByIdSucursal(idSucursal);
	}

	@Override
	public List<Pedido> obtenerPorMesa(Integer idMesa) {
		return pedidoRepository.findByIdMesa(idMesa);
	}

}



