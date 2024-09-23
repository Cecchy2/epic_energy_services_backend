package epic_team6.buildweek_epic_energy_services.controllers;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvincieController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file")MultipartFile file) throws Exception{
        List<Provincia> province = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings =new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.getFormat().setDelimiter(';');

        CsvParser parser = new CsvParser(settings);

        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Provincia provincia = new Provincia();
            provincia.setSigla(record.getString("Sigla"));
            provincia.setNome(record.getString("Provincia"));
            provincia.setRegione(record.getString("Regione"));

            province.add(provincia);
        });
        provinceService.saveAll(province);
        return "Upload delle provincie riuscito";
    }

}
