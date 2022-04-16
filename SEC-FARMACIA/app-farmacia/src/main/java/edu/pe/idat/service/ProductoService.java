package edu.pe.idat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pe.idat.model.Producto;
import edu.pe.idat.model.sp.ProductoSp;
import edu.pe.idat.repository.ProductoRepository;
import edu.pe.idat.repository.ProductoSpRepository;

@Service
@Transactional
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ProductoSpRepository productoSpRepository;
	
	public List<ProductoSp> listarProductos() {
		return productoSpRepository.listarProducto();
	}
	
	public ProductoSp listarProductoIdSp(Integer id) {
		return productoSpRepository.listaProductoId(id);
	}
	
	public Producto listarProductoId(Integer id) {
		return productoRepository.findById(id).get();
	}
	
	
	public Producto crearProducto(Producto pro) {
		return productoRepository.save(pro);
	}
	
	public void eliminarProducto(Integer id) {
		productoRepository.deleteById(id);
	}
	
	public void eliminarProductoVist(Producto pro) {
		productoRepository.deleteById(pro.getId_producto());
	}
	
	public void registrarProducto(Producto producto) {
		if (producto.getId_producto() == 0) {
			productoRepository.registrarProducto(
					producto.getImagenproducto(),
					producto.getNombreproducto(),
					producto.getPrescripcion(),
					producto.getPresentacion(),
					producto.getPrecio(),
					producto.getPreciounitario(),
					producto.getStock(),
					producto.getIs_active(),
					producto.getIdcategoria(),
					producto.getIdcategoria());
		} else {
			productoRepository.actualizarProducto(
					producto.getId_producto(),
					producto.getImagenproducto(),
					producto.getNombreproducto(),
					producto.getPrescripcion(),
					producto.getPresentacion(),
					producto.getPrecio(),
					producto.getPreciounitario(),
					producto.getStock(),
					producto.getIs_active(),
					producto.getIdcategoria(),
					producto.getIdcategoria());
		}
	}

	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}
	
}
