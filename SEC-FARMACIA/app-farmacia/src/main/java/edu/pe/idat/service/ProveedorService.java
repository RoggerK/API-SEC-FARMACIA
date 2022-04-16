package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pe.idat.model.Proveedor;
import edu.pe.idat.repository.ProveedorRepository;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedor() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }
    
    public void crear(Proveedor pro) {
    	proveedorRepository.save(pro);
    }
    
    public Proveedor bucarProveedorID(Integer id) {
    	return proveedorRepository.findById(id).get();
    }
    
    public void eliminarProveedorID(Integer id) {
        proveedorRepository.deleteById(id);
    }

    public void registrarProveedor(Proveedor proveedor) {
        if (proveedor.getIdproveedor() == 0) {
            proveedorRepository.registrarProveedor(
            		proveedor.getNombreproveedor(),
                    proveedor.getRucproveedor(),
                    proveedor.getTelefonoproveedor(),
                    proveedor.getDireccionproveedor(),
                    proveedor.getIsactive());
        } else {
            proveedorRepository.actualizarProveedor(proveedor.getIdproveedor(),
                    proveedor.getNombreproveedor(),
                    proveedor.getRucproveedor(),
                    proveedor.getTelefonoproveedor(),
                    proveedor.getDireccionproveedor(),
                    proveedor.getIsactive());
        }

    }
    
    public void eliminarProveedor(Proveedor pro) {
        proveedorRepository.deleteById(pro.getIdproveedor());
    }

}
