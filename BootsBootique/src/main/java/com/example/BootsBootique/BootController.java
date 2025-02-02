package com.example.BootsBootique;


import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collection;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/boots")
public class BootController {
    private final BootRepository bootRepository;

    public BootController(final BootRepository bootRepository) {
        this.bootRepository = bootRepository;
    }
    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        return this.bootRepository.findAll();
    }
    @GetMapping("/types")
    public List<String> getBootTypes() {
        return Arrays.stream(BootType.values())
                .map(Enum::name)
                .toList();
    }
    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        return this.bootRepository.save(boot);
    }
    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {
        Optional<Boot> bootToDeleteOptional = this.bootRepository.findById(id);
        if (!bootToDeleteOptional.isPresent()) {
            return null;
        }
        Boot bootToDelete = bootToDeleteOptional.get();
        this.bootRepository.delete(bootToDelete);
        return bootToDelete;
    }
    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootToIncrementOptional = this.bootRepository.findById(id);
        if (!bootToIncrementOptional.isPresent()) {
            return null;
        }
        Boot bootToIncrement = bootToIncrementOptional.get();
        bootToIncrement.setQuantity(bootToIncrement.getQuantity() + 1);
        this.bootRepository.save(bootToIncrement);
        return bootToIncrement;
    }
    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> bootToDecrementOptional = this.bootRepository.findById(id);
        if (!bootToDecrementOptional.isPresent()) {
            return null;
        }
        Boot bootToDecrement = bootToDecrementOptional.get();
        bootToDecrement.setQuantity(bootToDecrement.getQuantity() - 1);
        this.bootRepository.save(bootToDecrement);
        return bootToDecrement;
    }
    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) {
        if (Objects.nonNull(material)) {
            if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                return this.bootRepository.findByMaterialAndTypeAndSizeAndQuantityGreaterThan(material, type, size, minQuantity);
            } else if (Objects.nonNull(type) && Objects.nonNull(size)) {
                return this.bootRepository.findByMaterialAndTypeAndSize(material, type, size);
            } else if (Objects.nonNull(type) && Objects.nonNull(minQuantity)) {
                return this.bootRepository.findByMaterialAndTypeAndQuantityGreaterThan(material, type, minQuantity);
            } else if (Objects.nonNull(type)) {
                return this.bootRepository.findByMaterialAndType(material, type);
            } else {
                return this.bootRepository.findByMaterial(material);
            }
        } else if (Objects.nonNull(type)) {
            if (Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
                return this.bootRepository.findByTypeAndSizeAndQuantityGreaterThan(type, size, minQuantity);
            } else if (Objects.nonNull(size)) {
                return this.bootRepository.findByTypeAndSize(type, size);
            } else if (Objects.nonNull(minQuantity)) {
                return this.bootRepository.findByTypeAndQuantityGreaterThan(type, minQuantity);
            } else {
                return this.bootRepository.findByType(type);
            }
        } else if (Objects.nonNull(size)) {
            if (Objects.nonNull(minQuantity)) {
                return this.bootRepository.findBySizeAndQuantityGreaterThan(size, minQuantity);
            } else {
                return this.bootRepository.findBySize(size);
            }
        } else if (Objects.nonNull(minQuantity)) {
            return this.bootRepository.findByQuantityGreaterThan(minQuantity);
        } else {
            return new ArrayList<>((Collection<Boot>) this.bootRepository.findAll());
        }
    }
}