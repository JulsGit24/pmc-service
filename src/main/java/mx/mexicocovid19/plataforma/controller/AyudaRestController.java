package mx.mexicocovid19.plataforma.controller;

import mx.mexicocovid19.plataforma.ApiController;
import mx.mexicocovid19.plataforma.controller.dto.AyudaDTO;
import mx.mexicocovid19.plataforma.controller.mapper.AyudaMapper;
import mx.mexicocovid19.plataforma.service.AyudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by betuzo on 14/05/15.
 */
@Controller
public class AyudaRestController {

    @Autowired
    private AyudaService ayudaService;


    @ResponseBody
    @GetMapping(
            value = { ApiController.API_PATH_PUBLIC + "/ayuda" },
            produces = {"application/json;charset=UTF-8"})
    public List<AyudaDTO> readAyudas(@RequestParam(value = "origenAyuda", defaultValue = "AMBOS") final String origenAyuda,
                                     @RequestParam(value = "longitude") final Double longitude,
                                     @RequestParam(value = "latitude") final Double latitude,
                                     @RequestParam(value = "kilometers") final Integer kilometers) {
        return AyudaMapper.from(ayudaService.readAyudas(origenAyuda, longitude, latitude, kilometers));
    }

    @ResponseBody
    @PostMapping(
            value = { ApiController.API_PATH_PRIVATE + "/ayuda" },
            produces = {"application/json;charset=UTF-8"})
    public AyudaDTO createAyuda(@RequestBody AyudaDTO ayudaDTO, HttpServletRequest request) throws MessagingException {
        return AyudaMapper.from(ayudaService.createAyuda(AyudaMapper.from(ayudaDTO), request.getContextPath()));
    }
}
