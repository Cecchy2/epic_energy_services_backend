package epic_team6.buildweek_epic_energy_services.controllers;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public Page<Provincia> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "uuid") String sortBy) {
        return this.provinceService.findAll(page, size, sortBy);
    }

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        List<Provincia> province = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.getFormat().setDelimiter(';');

        CsvParser parser = new CsvParser(settings);

        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Provincia provincia = new Provincia();
            provincia.setSigla(record.getString("Sigla"));
            provincia.setNome(record.getString("Provincia"));
            provincia.setRegione(record.getString("Regione"));

            if (provincia.getNome().equalsIgnoreCase("Monza-Brianza")) {
                provincia.setNome("Monza e della Brianza");
            } else if (provincia.getNome().equalsIgnoreCase("Reggio-Emilia")) {
                provincia.setNome("Reggio nell'Emilia");
            } else if (provincia.getNome().equalsIgnoreCase("Ascoli-Piceno")) {
                provincia.setNome("Ascoli Piceno");
            } else if (provincia.getNome().equalsIgnoreCase("Pesaro-Urbino")) {
                provincia.setNome("Pesaro e Urbino");
            } else if (provincia.getNome().equalsIgnoreCase("Carbonia Iglesias")) {
                provincia.setNome("Sud Sardegna");
            } else if (provincia.getNome().equalsIgnoreCase("Verbania")) {
                provincia.setNome("Verbano-Cusio-Ossola");
            } else if (provincia.getNome().equalsIgnoreCase("Vibo-Valentia")) {
                provincia.setNome("Vibo Valentia");
            } else if (provincia.getNome().equalsIgnoreCase("Forli-Cesena")) {
                provincia.setNome("Forlì-Cesena");
            } else if (provincia.getNome().equalsIgnoreCase("Bolzano")) {
                provincia.setNome("Bolzano/Bozen");
            } else if (provincia.getNome().equalsIgnoreCase("Aosta")) {
                provincia.setNome("Valle d'Aosta/Vallée d'Aoste");
            } else if (provincia.getNome().equalsIgnoreCase("Reggio-Calabria")) {
                provincia.setNome("Reggio Calabria");
            } else if (provincia.getNome().equalsIgnoreCase("La-Spezia")) {
                provincia.setNome("La Spezia");
            }

            province.add(provincia);
        });
        provinceService.saveAll(province);
        return "Upload delle provincie riuscito";
    }

}
