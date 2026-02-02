package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.DetallePedido;
import web.Sushi_Inka.repository.DetallePedidoRepository;
import web.Sushi_Inka.service.IDetallePedidoService;

@Service
public class DetallePedidoService implements IDetallePedidoService {

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Override
	public List<DetallePedido> obtenerTodos() {
		return detallePedidoRepository.findAll();
	}

	@Override
	public DetallePedido obtenerPorId(Integer id) {
		return detallePedidoRepository.findById(id).orElse(null);
	}

	@Override
	public DetallePedido guardar(DetallePedido detallePedido) {
		return detallePedidoRepository.save(detallePedido);
	}

	@Override
	public DetallePedido actualizar(Integer id, DetallePedido detallePedido) {
		DetallePedido detallePedidoExistente = detallePedidoRepository.findById(id).orElse(null);
		if (detallePedidoExistente != null) {
			detallePedidoExistente.setIdPedido(detallePedido.getIdPedido());
			detallePedidoExistente.setIdPlato(detallePedido.getIdPlato());
			detallePedidoExistente.setCantidad(detallePedido.getCantidad());
			detallePedidoExistente.setPrecioUnitario(detallePedido.getPrecioUnitario());
			detallePedidoExistente.setSubtotal(detallePedido.getSubtotal());
			detallePedidoExistente.setNotas(detallePedido.getNotas());
			return detallePedidoRepository.save(detallePedidoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		detallePedidoRepository.deleteById(id);
	}

	@Override
	public List<DetallePedido> obtenerPorPedido(Integer idPedido) {
		return detallePedidoRepository.findByIdPedido(idPedido);
	}

}



