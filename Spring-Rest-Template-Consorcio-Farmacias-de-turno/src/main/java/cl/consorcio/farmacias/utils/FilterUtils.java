package cl.consorcio.farmacias.utils;

import cl.consorcio.farmacias.model.LocalesRegion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FilterUtils {

    public List<LocalesRegion> getListaFiltraComunaLocal(List<LocalesRegion> localesRegion, String comuna, String nombrelocal) {

        List<LocalesRegion> result = localesRegion.stream()
                .filter(filtraComuna -> comuna.equals(filtraComuna.getComuna_nombre()))
                .filter(filtraLocal -> nombrelocal.equals(filtraLocal.getLocal_nombre()))
                .collect(Collectors.toList());

        result.forEach(System.out::println);

        return result;

    }

}
