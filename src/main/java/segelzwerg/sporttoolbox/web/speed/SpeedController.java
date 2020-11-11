package segelzwerg.sporttoolbox.web.speed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import segelzwerg.sporttoolbox.iunits.Time;
import segelzwerg.sporttoolbox.iunits.distance.Distance;
import segelzwerg.sporttoolbox.iunits.speed.Speed;
import segelzwerg.sporttoolbox.web.DistanceAutoFillFactory;
import segelzwerg.sporttoolbox.web.Translator;

/**
 * Speed Controller
 */
@Controller
public class SpeedController {
    private final SpeedService service;
    private SpeedForm form = null;

    @Autowired
    public SpeedController(SpeedService service) {
        this.service = service;
    }

    /**
     * Get speed page
     *
     * @param model
     * @return speed page name
     */
    @GetMapping("/speed")
    public String string(Model model) {
        if (form == null) {
            form = new SpeedForm();
        }
        model.addAttribute("form", form);

        return Translator.toLocale("SpeedForm");
    }

    /**
     * Calculate speed
     *
     * @param model
     * @param form
     * @return calculated speed
     */
    @PostMapping("/speed")
    public String speedCalculating(Model model, SpeedForm form) {
        model.addAttribute("form", form);
        if (form.getSpeed() == 0) {
            Speed speed = service.calculateSpeed(form);
            SpeedPresenter speedPresenter = new SpeedPresenter(speed);
            model.addAttribute("speed", speedPresenter);
        } else if (form.getHour() == 0 && form.getMinute() == 0 && form.getSecond() == 0) {
            Time time = service.calculateTime(form);
            model.addAttribute("time", time);
        } else if (form.getMajor() == 0 && form.getMinor() == 0) {
            Distance distance = service.calculateDistance(form);
            model.addAttribute("distance", distance);
        }
        return Translator.toLocale("SpeedForm");
    }

    /**
     * fills out some distances values from template
     *
     * @param model
     * @param distance
     * @return
     */
    @PostMapping("/speed/autodistance")
    public String autoDistance(Model model, String distance) {
        form = DistanceAutoFillFactory.autoDistance(distance);
        return "redirect:/speed";
    }
}
