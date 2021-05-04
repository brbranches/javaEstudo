package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	// DEfinindo uma String final em que ela recebe o caminho que representa os
	// relatórios.
	private static final String FOLDER_RELATORIOS = "/relatorios";

	// Definindo subrelatórios, quando tem relacionamento de 1 para muitos (1-*).
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";

	// Definindo o separador para trabalhar com arquivos. São as barras \ ou /,
	// dependend do sistema operacional.
	private String SEPARATOR = File.separator;
	private static String CaminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;

	// Vai receber uma lista de dados que vai servir de base para gerar o relatório
	public String gerarRelatorio(List<?> ListaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {

		/*
		 * Cria a lista de colelctionDataSource de beans que carregam os dados para
		 * montar o relatório.
		 */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(ListaDataBeanCollection);

		/* Fornece o caminho fisico até a pasta que contem os relatórios .jasper */
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

		if (caminhoRelatorio == null || caminhoRelatorio != null && caminhoRelatorio.isEmpty() || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		/*Caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/*caminho completo até o relatório compilado indicado*/
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		/*Faz o carregamento do relatório*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		
		/*Setando os parâmetros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		/*Carregando o arquivo para a memória, passo o relatório, os parametros e os dados da lista*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		exporter = new JRPdfExporter();
		
		/*Caminho do relatório exportado*/
		CaminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
		
		/*Criando novo arquivo exportado*/
		arquivoGerado = new File(CaminhoArquivoRelatorio);
		
		/*Prepara a impressão*/
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		/*Dando nome de saída para o relatório*/
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Executando a exportação do arquivo*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor após ser feito o download, para nao pesar o sistema*/
		arquivoGerado.deleteOnExit();
		
		return CaminhoArquivoRelatorio;
		

	}

}
