package com.vm.infrasw.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vm.infrasw.models.Maquina;

@Controller
@RequestMapping(path="/vm")
public class MainController {
	
	@PostMapping(path="/create")
	public @ResponseBody ResponseEntity<Object> criarMaquina(@RequestBody Maquina maquina) throws InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process process = null;
	
		try {
			process = rt.exec("VBoxManage createvm --name \"" + maquina.getNome() + "\" --ostype \"Ubuntu_64\" --register");
			
			while(process.waitFor() != 0) {}
			System.out.println("Criou máquina");
			
			process = rt.exec("VBoxManage modifyvm \"" + maquina.getNome() + "\" --cpuhotplug on");
			
			while(process.waitFor() != 0) {}
			System.out.println("Marcou CPU como Hot Plug");
			
			process = rt.exec("VBoxManage modifyvm \"" + maquina.getNome() + "\" --cpus " + maquina.getNucleos());
			
			while(process.waitFor() != 0) {}
			System.out.println("Configurou número de núcleos");
			
			process = rt.exec("VBoxManage modifyvm \"" + maquina.getNome() + "\" --memory " + maquina.getRam() + " --acpi on --boot1 dvd");
			
			while(process.waitFor() != 0) {}
			System.out.println("passou 2");
			
			process = rt.exec("VBoxManage createhd --filename ./" + maquina.getNome() + ".vdi --size " + maquina.getArmazenamento() * 1000);
			
			while(process.waitFor() != 0) {}
			System.out.println("passou 3 ");
			
			process = rt.exec("VBoxManage storagectl \"" + maquina.getNome() + "\" --name \"IDE Controller\" --add ide");
			
			while(process.waitFor() != 0) {}
			System.out.println("passou 4");
			
			process = rt.exec("VBoxManage storageattach \"" + maquina.getNome() + "\" --storagectl \"IDE Controller\" --port 0 --device 0 --type hdd --medium ./" + maquina.getNome() + ".vdi");
			
			while(process.waitFor() != 0) {}
			System.out.println("passou 5");
			
			process = rt.exec("VBoxManage storageattach \"" + maquina.getNome() + "\" --storagectl \"IDE Controller\" --port 1 --device 0 --type dvddrive --medium \"C:\\Users\\edvan.soares\\Downloads\\ubuntu-16.04.2-desktop-amd64.iso\"");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>("{\"status\": 0}", HttpStatus.OK); 
	}

}
