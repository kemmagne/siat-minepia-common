package org.guce.siat.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.data.FieldGroupDto;
import org.guce.siat.common.model.FieldGroup;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.FileItemFieldValue;
import org.guce.siat.common.service.ApplicationPropretiesService;



/**
 * The Class JsfUtil.
 */
public final class RepetableUtil
{


	/**
	 * Instantiates a new jsf util.
	 */
	private RepetableUtil()
	{

	}

	private static final String LOCAL_BUNDLE_NAME = "org.guce.siat.messages.locale";
	private static Locale locale;

	/**
	 * Generate report.
	 *
	 * @param fileFieldValue
	 *           the file field value
	 * @param rowSeparator
	 *           the row separator
	 * @return the string[]
	 */
	//	public static void generateReport(final AbstractReportInvoker abstractReportInvoker)
	//	{
	//		abstractReportInvoker.exportReportToPdf();
	//	}


	public static String[] generateArrayValues(final FileFieldValue fileFieldValue, final String rowSeparator)
	{
		String[] arrayValues = null;

		if ((fileFieldValue != null) && StringUtils.isNotEmpty(fileFieldValue.getValue()))
		{
			arrayValues = fileFieldValue.getValue().split(rowSeparator);
		}

		return arrayValues;
	}



	/**
	 * Generate array item values.
	 *
	 * @param fileItemFieldValue
	 *           the file item field value
	 * @param rowSeparator
	 *           the row separator
	 * @return the string[]
	 */
	public static String[] generateArrayItemValues(final FileItemFieldValue fileItemFieldValue, final String rowSeparator)
	{
		String[] arrayValues = null;

		if ((fileItemFieldValue != null) && StringUtils.isNotEmpty(fileItemFieldValue.getValue()))
		{
			arrayValues = fileItemFieldValue.getValue().split(rowSeparator);
		}

		return arrayValues;
	}


	/**
	 * Generate array item values.
	 *
	 * @param fileFieldValue
	 *           the file field value
	 * @param rowSeparator
	 *           the row separator
	 * @return the string[]
	 */
	public static String[] generateArrayItemValues(final FileFieldValue fileFieldValue, final String rowSeparator)
	{
		String[] arrayValues = null;

		if ((fileFieldValue != null) && StringUtils.isNotEmpty(fileFieldValue.getValue()))
		{
			arrayValues = fileFieldValue.getValue().split(rowSeparator);
		}
		return arrayValues;
	}


	/**
	 * Generate table content.
	 *
	 * @param arrayValues
	 *           the array values
	 * @param columnSeparator
	 *           the column separator
	 * @return the list
	 */
	public static List<Map<Integer, String>> generateTableContent(final String[] arrayValues, final String columnSeparator)
	{

		List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();

		if (arrayValues != null)
		{
			final String[] columns = arrayValues[0].split(columnSeparator);
			if (columns != null)
			{
				final int colNumber = columns.length;
				for (int i = 1; i < arrayValues.length; i++)
				{
					final HashMap<Integer, String> map = new HashMap<Integer, String>();
					final String[] rows = arrayValues[i].split(columnSeparator);
					if (rows != null && rows.length == colNumber)
					{
						for (int j = 0; j < rows.length; j++)
						{
							map.put(j, rows[j]);
						}
						list.add(map);
					}
					else
					{
						list = null;
						break;
					}
				}
			}
		}

		return list;
	}


	/** The application propreties service. */
	static ApplicationPropretiesService applicationPropretiesService;
	private static FileItem selectedFileItem;



	/**
	 * Inits the dynamic tabs.
	 *
	 * @param repeatablefileFieldValueList2
	 *           the repeatablefile field value list2
	 * @param row
	 *           the row
	 *
	 * @return the list
	 */
	public static List<Tab> initDynamicTabs(final List<FileItemFieldValue> repeatablefileFieldValueList2, final Integer row)
	{

		final List<Tab> tabList = new ArrayList<Tab>();

		if (CollectionUtils.isNotEmpty(repeatablefileFieldValueList2))
		{
			for (int i = 0; i < repeatablefileFieldValueList2.size(); i++)
			{
				final Tab tab = new Tab();

				final List<TableColHeader> colHeaders = new ArrayList<TableColHeader>();
				// if(repeatablefileFieldValueList.get(i).getValue().contains("#"));
				// tab.setFils(true);

				final String[] colunmsFk = generateArrayItemValues(repeatablefileFieldValueList2.get(i),
						applicationPropretiesService.getRowSeparator());

				final String[] column = colunmsFk[0].split(applicationPropretiesService.getColumnSeparator() + ""
						+ applicationPropretiesService.getRepeatableSeparator());
				if (column.length > 1)
				{ // détécter si il y a un bloc répétable
				  // sup
					tab.setFils(true);
					final List<String> repetable = new ArrayList<String>();
					for (int j = 1; j < column.length; j++)
					{
						repetable.add(column[j]);
					}
					tab.setRepetable(repetable);
				}
				final String[] arrayValues = new String[colunmsFk.length];
				arrayValues[0] = column[0];

				for (int j = 1; j < colunmsFk.length; j++)
				{
					arrayValues[j] = colunmsFk[j];
				}

				final List<Map<Integer, String>> tableContent = generateTableContent(arrayValues, applicationPropretiesService

				.getColumnSeparator());
				if (row != null)
				{
					final List<Integer> fils = recupererFilsByParent(row, repeatablefileFieldValueList2.get(i));
					final List<Map<Integer, String>> tableContent1 = new ArrayList<Map<Integer, String>>();
					for (final Integer rowP : fils)
					{
						tableContent1.add(tableContent.get(rowP));
					}
					tab.setTable(tableContent1);
				}
				else
				{
					tab.setTable(tableContent);
				}

				if (arrayValues != null)
				{
					final String[] columns = arrayValues[0].split(applicationPropretiesService.getColumnSeparator());

					if (columns != null)
					{
						for (int j = 0; j < columns.length; j++)
						{
							final TableColHeader tableColHeader = new TableColHeader();
							tableColHeader.setLabelFr(columns[j]);
							tableColHeader.setLabelEn(columns[j]);

							colHeaders.add(tableColHeader);

						}
					}
				}
				// si le nombre des columns est supérieur à 3
				if (colHeaders.size() > 3)
				{
					final List<TableColHeader> headers = new ArrayList<TableColHeader>();

					for (int j = 0; j < 3; j++)
					{
						headers.add(colHeaders.get(j));
					}
					tab.setColHeader(headers);
				}
				tab.setLabelEn(repeatablefileFieldValueList2.get(i).getFileItemField().getLabelEn());
				tab.setLabelFr(repeatablefileFieldValueList2.get(i).getFileItemField().getLabelFr());
				tab.setCode(repeatablefileFieldValueList2.get(i).getFileItemField().getCode());
				tab.setTableColHeader(colHeaders);

				tabList.add(tab);
			}
		}
		return tabList;
	}

	/**
	 * Inits the dynamic tabs.
	 *
	 * @param repeatablefileFieldValueList2
	 *           the repeatablefile field value list2
	 *
	 * @return the list
	 */
	public static List<Tab> initDynamicTabs(final List<FileFieldValue> repeatablefileFieldValueList2)
	{

		final List<Tab> tabList = new ArrayList<Tab>();

		if (CollectionUtils.isNotEmpty(repeatablefileFieldValueList2))
		{
			for (int i = 0; i < repeatablefileFieldValueList2.size(); i++)
			{
				final Tab tab = new Tab();

				final List<TableColHeader> colHeaders = new ArrayList<TableColHeader>();
				// if(repeatablefileFieldValueList.get(i).getValue().contains("#"));
				// tab.setFils(true);

				final String[] colunmsFk = generateArrayItemValues(repeatablefileFieldValueList2.get(i),
						applicationPropretiesService.getRowSeparator());

				final String[] column = colunmsFk[0].split(applicationPropretiesService.getColumnSeparator() + ""
						+ applicationPropretiesService.getRepeatableSeparator());
				if (column.length > 1)
				{ // détécter si il y a un bloc répétable
				  // sup
					tab.setFils(true);
					final List<String> repetable = new ArrayList<String>();
					for (int j = 1; j < column.length; j++)
					{
						repetable.add(column[j]);
					}
					tab.setRepetable(repetable);
				}
				final String[] arrayValues = new String[colunmsFk.length];
				arrayValues[0] = column[0];

				for (int j = 1; j < colunmsFk.length; j++)
				{
					arrayValues[j] = colunmsFk[j];
				}

				final List<Map<Integer, String>> tableContent = generateTableContent(arrayValues, applicationPropretiesService

				.getColumnSeparator());

				tab.setTable(tableContent);

				if (arrayValues != null)
				{
					final String[] columns = arrayValues[0].split(applicationPropretiesService.getColumnSeparator());

					if (columns != null)
					{
						for (int j = 0; j < columns.length; j++)
						{
							final TableColHeader tableColHeader = new TableColHeader();
							tableColHeader.setLabelFr(columns[j]);
							tableColHeader.setLabelEn(columns[j]);

							colHeaders.add(tableColHeader);

						}
					}
				}
				// si le nombre des columns est supérieur à 3
				if (colHeaders.size() > 3)
				{
					final List<TableColHeader> headers = new ArrayList<TableColHeader>();

					for (int j = 0; j < 3; j++)
					{
						headers.add(colHeaders.get(j));
					}
					tab.setColHeader(headers);
				}
				tab.setLabelEn(repeatablefileFieldValueList2.get(i).getFileField().getLabelEn());
				tab.setLabelFr(repeatablefileFieldValueList2.get(i).getFileField().getLabelFr());
				tab.setCode(repeatablefileFieldValueList2.get(i).getFileField().getCode());
				tab.setTableColHeader(colHeaders);

				tabList.add(tab);
			}
		}
		return tabList;
	}


	/**
	 * Recuperer fils by parent.
	 *
	 * @param row
	 *           the row
	 * @param fileItemFieldValue
	 *           the file item field value
	 * @return the list
	 */
	private static List<Integer> recupererFilsByParent(final Integer row, final FileItemFieldValue fileItemFieldValue)
	{
		final List<Integer> rows = new ArrayList<Integer>();
		final String value = fileItemFieldValue.getHistory();
		final String[] array = value.split(";");
		if (array.length > 0)
		{
			final String[] rowS = array[row].split(",");
			for (int i = 0; i < rowS.length; i++)
			{
				if (StringUtils.isNotBlank(rowS[i]))
				{
					rows.add(Integer.parseInt(rowS[i]));
				}

			}
		}
		return rows;
	}



	/**
	 * Group file item field values.
	 *
	 * @param nonRepetableFiledValues
	 *           the non repetable filed values
	 * @param repetablefileFiledValues
	 *           the repetablefile filed values
	 * @param fieldGroups
	 *           the field groups
	 * @param applicationPropretiesService
	 *           the application propreties service
	 * @param fileItem
	 *           the file item
	 * @param locale
	 *           the locale
	 * @return the list
	 */
	public static List<FieldGroupDto<FileFieldValue>> groupFileFieldValues(final List<FileFieldValue> nonRepetableFiledValues,
			final List<FileFieldValue> repetablefileFiledValues, final List<FieldGroup> fieldGroups,
			final ApplicationPropretiesService applicationPropretiesService, final FileItem fileItem, final Locale locale)
	{
		RepetableUtil.locale = locale;
		RepetableUtil.selectedFileItem = fileItem;
		RepetableUtil.applicationPropretiesService = applicationPropretiesService;
		final List<FieldGroupDto<FileFieldValue>> fileFieldGroupDtos = new ArrayList<FieldGroupDto<FileFieldValue>>();
		for (final FieldGroup fieldGroup : fieldGroups)
		{
			final FieldGroupDto<FileFieldValue> fileFieldGroupDto = new FieldGroupDto<FileFieldValue>();
			fileFieldGroupDto.setLabelFr(fieldGroup.getLabelFr());
			fileFieldGroupDto.setLabelEn(fieldGroup.getLabelEn());
			final List<FileFieldValue> listFileFieldValues = new ArrayList<FileFieldValue>();
			final List<Tab> tabs = new ArrayList<Tab>();
			for (final FileFieldValue fileFieldValue : nonRepetableFiledValues)
			{
				if (fieldGroup.getId().equals(fileFieldValue.getFileField().getGroup().getId()))
				{
					listFileFieldValues.add(fileFieldValue);
					// tabs.add(initDynamicTabs(listFileFieldValues).get(0));
				}
			}



			for (final FileFieldValue repFileItemFieldValue : repetablefileFiledValues)
			{
				if (fieldGroup.getId().equals(repFileItemFieldValue.getFileField().getGroup().getId()))
				{
					final List<FileFieldValue> repetables = new ArrayList<FileFieldValue>();
					repetables.add(repFileItemFieldValue);
					tabs.add(initDynamicTabs(repetables).get(0));

				}
			}

			fileFieldGroupDto.setFieldValues(listFileFieldValues);
			if (fieldGroup.getLabelEn().equals("General"))
			{
				populateFileGeneralGroup(fileFieldGroupDto);
			}

			if (fieldGroup.getLabelEn().equals("Informations Générales"))
			{
				//Pays de provenance GeneralInformationLabel_provenaceCountry
				if (selectedFileItem.getFile().getCountryOfProvenance() != null
						&& StringUtils.isNotBlank(selectedFileItem.getFile().getCountryOfProvenance().getCountryIdAlpha2()))
				{
					final String countryOfProvenanceCodeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
							.getString("GeneralInformationLabel_provenanceCountryCode");
					final String countryOfProvenanceCodeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
							.getString("GeneralInformationLabel_provenanceCountryCode");
					final FileField fileFieldCountryOfProvenanceCode = new FileField();
					fileFieldCountryOfProvenanceCode.setLabelFr(countryOfProvenanceCodeLabelFr);
					fileFieldCountryOfProvenanceCode.setLabelEn(countryOfProvenanceCodeLabelEn);
					final FileFieldValue countryOfProvenanceCode = new FileFieldValue();
					countryOfProvenanceCode.setValue(selectedFileItem.getFile().getCountryOfProvenance().getCountryIdAlpha2());
					countryOfProvenanceCode.setFileField(fileFieldCountryOfProvenanceCode);
					listFileFieldValues.add(countryOfProvenanceCode);
				}
				if (selectedFileItem.getFile().getCountryOfProvenance() != null)
				{
					final String provenaceCountryFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
							"GeneralInformationLabel_provenaceCountry");
					final String provenaceCountryEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
							"GeneralInformationLabel_provenaceCountry");
					final FileField fileFieldProvenaceCountry = new FileField();
					fileFieldProvenaceCountry.setLabelFr(provenaceCountryFr);
					fileFieldProvenaceCountry.setLabelEn(provenaceCountryEn);
					final FileFieldValue provenaceCountry = new FileFieldValue();
					provenaceCountry.setValue(locale.getLanguage().equals("en") ? selectedFileItem.getFile().getCountryOfProvenance()
							.getCountryName() : selectedFileItem.getFile().getCountryOfProvenance().getCountryNameFr());
					provenaceCountry.setFileField(fileFieldProvenaceCountry);
					listFileFieldValues.add(provenaceCountry);

				}

				if (selectedFileItem.getFile().getCountryOfOrigin() != null)
				{
					if (StringUtils.isNotBlank(selectedFileItem.getFile().getCountryOfOrigin().getCountryIdAlpha2()))
					{
						final String countryOfOriginCodeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
								"GeneralInformationLabel_originCountryCode");
						final String countryOfOriginCodeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
								.getString("GeneralInformationLabel_originCountryCode");
						final FileField fileFieldCountryOfOriginCode = new FileField();
						fileFieldCountryOfOriginCode.setLabelFr(countryOfOriginCodeLabelFr);
						fileFieldCountryOfOriginCode.setLabelEn(countryOfOriginCodeLabelEn);
						final FileFieldValue countryOfOriginCode = new FileFieldValue();
						countryOfOriginCode.setValue(selectedFileItem.getFile().getCountryOfOrigin().getCountryIdAlpha2());
						countryOfOriginCode.setFileField(fileFieldCountryOfOriginCode);
						listFileFieldValues.add(countryOfOriginCode);
					}

					if (StringUtils.isNotBlank(selectedFileItem.getFile().getCountryOfOrigin().getCountryName()))
					{

						final String countryOfOriginLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
								"GeneralInformationLabel_originCountry");
						final String countryOfOriginLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
								"GeneralInformationLabel_originCountry");
						final FileField fileFieldCountryOfOrigin = new FileField();
						fileFieldCountryOfOrigin.setLabelFr(countryOfOriginLabelFr);
						fileFieldCountryOfOrigin.setLabelEn(countryOfOriginLabelEn);
						final FileFieldValue countryOfOrigin = new FileFieldValue();
						countryOfOrigin.setValue(selectedFileItem.getFile().getCountryOfOrigin().getCountryName());
						countryOfOrigin.setFileField(fileFieldCountryOfOrigin);
						listFileFieldValues.add(countryOfOrigin);
					}
				}

				if (selectedFileItem.getFile().getCountryOfDestination() != null)
				{
					if (StringUtils.isNotBlank(selectedFileItem.getFile().getCountryOfDestination().getCountryIdAlpha2()))
					{
						final String countryOfDestinationCodeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
								.getString("GeneralInformationLabel_destinationCountryCode");
						final String countryOfDestinationCodeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
								.getString("GeneralInformationLabel_destinationCountryCode");
						final FileField fileFieldCountryOfDestinationCode = new FileField();
						fileFieldCountryOfDestinationCode.setLabelFr(countryOfDestinationCodeLabelFr);
						fileFieldCountryOfDestinationCode.setLabelEn(countryOfDestinationCodeLabelEn);
						final FileFieldValue countryOfDestinationCode = new FileFieldValue();
						countryOfDestinationCode.setValue(selectedFileItem.getFile().getCountryOfDestination().getCountryIdAlpha2());
						countryOfDestinationCode.setFileField(fileFieldCountryOfDestinationCode);
						listFileFieldValues.add(countryOfDestinationCode);
					}

					if (StringUtils.isNotBlank(selectedFileItem.getFile().getCountryOfDestination().getCountryName()))
					{
						final String countryOfDestinationLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
								.getString("GeneralInformationLabel_destinationCountry");
						final String countryOfDestinationLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
								.getString("GeneralInformationLabel_destinationCountry");
						final FileField fileFieldCountryOfDestination = new FileField();
						fileFieldCountryOfDestination.setLabelFr(countryOfDestinationLabelFr);
						fileFieldCountryOfDestination.setLabelEn(countryOfDestinationLabelEn);
						final FileFieldValue countryOfDestination = new FileFieldValue();
						countryOfDestination.setValue(selectedFileItem.getFile().getCountryOfDestination().getCountryName());
						countryOfDestination.setFileField(fileFieldCountryOfDestination);
						listFileFieldValues.add(countryOfDestination);
					}
				}
			}
			if (!tabs.isEmpty())
			{
				fileFieldGroupDto.setTabs(tabs);
			}

			if (!listFileFieldValues.isEmpty() || !tabs.isEmpty())
			{
				fileFieldGroupDtos.add(fileFieldGroupDto);
			}
		}
		return fileFieldGroupDtos;
	}


	/**
	 * Group file field values.
	 *
	 * @param nonRepetablefileItemFiledValues
	 *           the non repetablefile item filed values
	 * @param repetablefileItemFiledValues
	 *           the repetablefile item filed values
	 * @param fieldGroups
	 *           the field groups
	 * @param applicationPropretiesService
	 *           the application propreties service
	 * @return the list
	 */
	public static List<FieldGroupDto<FileItemFieldValue>> groupFileItemFieldValues(
			final List<FileItemFieldValue> nonRepetablefileItemFiledValues,
			final List<FileItemFieldValue> repetablefileItemFiledValues, final List<FieldGroup> fieldGroups,
			final ApplicationPropretiesService applicationPropretiesService)
	{
		RepetableUtil.applicationPropretiesService = applicationPropretiesService;
		final List<FieldGroupDto<FileItemFieldValue>> fileFieldGroupDtos = new ArrayList<FieldGroupDto<FileItemFieldValue>>();
		for (final FieldGroup fieldGroup : fieldGroups)
		{
			final FieldGroupDto<FileItemFieldValue> fileFieldGroupDto = new FieldGroupDto<FileItemFieldValue>();
			fileFieldGroupDto.setLabelFr(fieldGroup.getLabelFr());
			fileFieldGroupDto.setLabelEn(fieldGroup.getLabelEn());
			final List<FileItemFieldValue> listFileFieldValues = new ArrayList<FileItemFieldValue>();
			final List<Tab> tabs = new ArrayList<Tab>();
			for (final FileItemFieldValue fileFieldValue : nonRepetablefileItemFiledValues)
			{
				if (fieldGroup.getId().equals(fileFieldValue.getFileItemField().getGroup().getId()))
				{
					listFileFieldValues.add(fileFieldValue);
					// tabs.add(initDynamicTabs(listFileFieldValues).get(0));
				}
			}

			for (final FileItemFieldValue repFileItemFieldValue : repetablefileItemFiledValues)
			{
				if (fieldGroup.getId().equals(repFileItemFieldValue.getFileItemField().getGroup().getId()))
				{
					final List<FileItemFieldValue> repetables = new ArrayList<FileItemFieldValue>();
					repetables.add(repFileItemFieldValue);
					tabs.add(initDynamicTabs(repetables, null).get(0));

				}
			}

			fileFieldGroupDto.setFieldValues(listFileFieldValues);
			if (!tabs.isEmpty())
			{
				fileFieldGroupDto.setTabs(tabs);
			}

			if (!listFileFieldValues.isEmpty() || !tabs.isEmpty())
			{
				fileFieldGroupDtos.add(fileFieldGroupDto);
			}
		}
		return fileFieldGroupDtos;
	}

	private static void populateFileGeneralGroup(final FieldGroupDto<FileFieldValue> fileFieldGroupDto)
	{
		final List<FileFieldValue> fieldValues = fileFieldGroupDto.getFieldValues();

		// File.Client
		if (selectedFileItem.getFile().getClient() != null)
		{
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCompanyName()))
			{
				final String clientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_Client");
				final String clientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_Client");
				final FileField fileFieldClient = new FileField();
				fileFieldClient.setLabelFr(clientLabelFr);
				fileFieldClient.setLabelEn(clientLabelEn);
				final FileFieldValue client = new FileFieldValue();
				client.setValue(selectedFileItem.getFile().getClient().getCompanyName());
				client.setFileField(fileFieldClient);
				fieldValues.add(0, client);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getNumContribuable()))
			{
				final String numContribuableClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationTitle_trNumCont");
				final String numContribuableClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationTitle_trNumCont");
				final FileField fileFieldnumContribuableClient = new FileField();
				fileFieldnumContribuableClient.setLabelFr(numContribuableClientLabelFr);
				fileFieldnumContribuableClient.setLabelEn(numContribuableClientLabelEn);
				final FileFieldValue numContribuableClient = new FileFieldValue();
				numContribuableClient.setValue(selectedFileItem.getFile().getClient().getNumContribuable());
				numContribuableClient.setFileField(fileFieldnumContribuableClient);
				fieldValues.add(0, numContribuableClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getTradeRegisterNumber()))
			{
				final String commercialRegistryNumberClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_commercialRegistryNumberClient");
				final String commercialRegistryNumberClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_commercialRegistryNumberClient");
				final FileField fileFieldcommercialRegistryNumberClient = new FileField();
				fileFieldcommercialRegistryNumberClient.setLabelFr(commercialRegistryNumberClientLabelFr);
				fileFieldcommercialRegistryNumberClient.setLabelEn(commercialRegistryNumberClientLabelEn);
				final FileFieldValue commercialRegistryNumberClient = new FileFieldValue();
				commercialRegistryNumberClient.setValue(selectedFileItem.getFile().getClient().getTradeRegisterNumber());
				commercialRegistryNumberClient.setFileField(fileFieldcommercialRegistryNumberClient);
				fieldValues.add(0, commercialRegistryNumberClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getFullAddress()))
			{
				final String adresseClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_adresseClient");
				final String adresseClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_adresseClient");
				final FileField fileFieldadresseClient = new FileField();
				fileFieldadresseClient.setLabelFr(adresseClientLabelFr);
				fileFieldadresseClient.setLabelEn(adresseClientLabelEn);
				final FileFieldValue adresseClient = new FileFieldValue();
				adresseClient.setValue(selectedFileItem.getFile().getClient().getFullAddress());
				adresseClient.setFileField(fileFieldadresseClient);
				fieldValues.add(0, adresseClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPostalCode()))
			{
				final String bpClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_bpClient");
				final String bpClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_bpClient");
				final FileField fileFieldbpClient = new FileField();
				fileFieldbpClient.setLabelFr(bpClientLabelFr);
				fileFieldbpClient.setLabelEn(bpClientLabelEn);
				final FileFieldValue bpClient = new FileFieldValue();
				bpClient.setValue(selectedFileItem.getFile().getClient().getPostalCode());
				bpClient.setFileField(fileFieldbpClient);
				fieldValues.add(0, bpClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCity()))
			{
				final String cityClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_cityClient");
				final String cityClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_cityClient");
				final FileField fileFieldcityClient = new FileField();
				fileFieldcityClient.setLabelFr(cityClientLabelFr);
				fileFieldcityClient.setLabelEn(cityClientLabelEn);
				final FileFieldValue cityClient = new FileFieldValue();
				cityClient.setValue(selectedFileItem.getFile().getClient().getCity());
				cityClient.setFileField(fileFieldcityClient);
				fieldValues.add(0, cityClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getEmail()))
			{
				final String mailClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_mailClient");
				final String mailClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_mailClient");
				final FileField fileFieldmailClient = new FileField();
				fileFieldmailClient.setLabelFr(mailClientLabelFr);
				fileFieldmailClient.setLabelEn(mailClientLabelEn);
				final FileFieldValue mailClient = new FileFieldValue();
				mailClient.setValue(selectedFileItem.getFile().getClient().getEmail());
				mailClient.setFileField(fileFieldmailClient);
				fieldValues.add(0, mailClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPhone()))
			{
				final String fixeNumberClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_fixeNumberClient");
				final String fixeNumberClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_fixeNumberClient");
				final FileField fileFieldfixeNumberClient = new FileField();
				fileFieldfixeNumberClient.setLabelFr(fixeNumberClientLabelFr);
				fileFieldfixeNumberClient.setLabelEn(fixeNumberClientLabelEn);
				final FileFieldValue fixeNumberClient = new FileFieldValue();
				fixeNumberClient.setValue(selectedFileItem.getFile().getClient().getPhone());
				fixeNumberClient.setFileField(fileFieldfixeNumberClient);
				fieldValues.add(0, fixeNumberClient);
			}

			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getFax()))
			{
				final String faxClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_faxClient");
				final String faxClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_faxClient");
				final FileField fileFieldfaxClient = new FileField();
				fileFieldfaxClient.setLabelFr(faxClientLabelFr);
				fileFieldfaxClient.setLabelEn(faxClientLabelEn);
				final FileFieldValue faxClient = new FileFieldValue();
				faxClient.setValue(selectedFileItem.getFile().getClient().getFax());
				faxClient.setFileField(fileFieldfaxClient);
				fieldValues.add(0, faxClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getMobile()))
			{
				final String mobileClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_mobileClient");
				final String mobileClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_mobileClient");
				final FileField fileFieldMobileClient = new FileField();
				fileFieldMobileClient.setLabelFr(mobileClientLabelFr);
				fileFieldMobileClient.setLabelEn(mobileClientLabelEn);
				final FileFieldValue mobileClient = new FileFieldValue();
				mobileClient.setValue(selectedFileItem.getFile().getClient().getMobile());
				mobileClient.setFileField(fileFieldMobileClient);
				fieldValues.add(0, mobileClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getWebSite()))
			{
				final String webSiteClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_webSiteClient");
				final String webSiteClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_webSiteClient");
				final FileField fileFieldWebSiteClient = new FileField();
				fileFieldWebSiteClient.setLabelFr(webSiteClientLabelFr);
				fileFieldWebSiteClient.setLabelEn(webSiteClientLabelEn);
				final FileFieldValue webSiteClient = new FileFieldValue();
				webSiteClient.setValue(selectedFileItem.getFile().getClient().getWebSite());
				webSiteClient.setFileField(fileFieldWebSiteClient);
				fieldValues.add(0, webSiteClient);
			}

			if (selectedFileItem.getFile().getClient().getCountry() != null
					&& StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCountry().getCountryIdAlpha2()))
			{
				final String countryClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_countryCodeClient");
				final String countryClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_countryCodeClient");
				final FileField fileFieldCountryClient = new FileField();
				fileFieldCountryClient.setLabelFr(countryClientLabelFr);
				fileFieldCountryClient.setLabelEn(countryClientLabelEn);
				final FileFieldValue countyClient = new FileFieldValue();
				countyClient.setValue(selectedFileItem.getFile().getClient().getCountry().getCountryIdAlpha2());
				countyClient.setFileField(fileFieldCountryClient);
				fieldValues.add(countyClient);
			}
			if (selectedFileItem.getFile().getClient().getCountry() != null)
			{
				final String countryClientLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_countryClient");
				final String countryClientLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_countryClient");
				final FileField fileFieldCountryClient = new FileField();
				fileFieldCountryClient.setLabelFr(countryClientLabelFr);
				fileFieldCountryClient.setLabelEn(countryClientLabelEn);
				final FileFieldValue countryClient = new FileFieldValue();
				countryClient.setValue(locale.getLanguage().equals("en") ? selectedFileItem.getFile().getClient().getCountry()
						.getCountryName() : selectedFileItem.getFile().getClient().getCountry().getCountryNameFr());
				countryClient.setFileField(fileFieldCountryClient);
				fieldValues.add(countryClient);

			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getBusinessApprovalType()))
			{
				final String businessApprovalTypeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_businessApprovalType");
				final String businessApprovalTypeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_businessApprovalType");
				final FileField fileFieldbusinessApprovalTypeClient = new FileField();
				fileFieldbusinessApprovalTypeClient.setLabelFr(businessApprovalTypeLabelFr);
				fileFieldbusinessApprovalTypeClient.setLabelEn(businessApprovalTypeLabelEn);
				final FileFieldValue businessApprovalTypeClient = new FileFieldValue();
				businessApprovalTypeClient.setValue(selectedFileItem.getFile().getClient().getBusinessApprovalType());
				businessApprovalTypeClient.setFileField(fileFieldbusinessApprovalTypeClient);
				fieldValues.add(0, businessApprovalTypeClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getBusinessApprovalRegistrationNumber()))
			{
				final String businessApprovalRegistrationNumberLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_businessApprovalRegistrationNumber");
				final String businessApprovalRegistrationNumberLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_businessApprovalRegistrationNumber");
				final FileField fileFieldbusinessApprovalRegistrationNumberClient = new FileField();
				fileFieldbusinessApprovalRegistrationNumberClient.setLabelFr(businessApprovalRegistrationNumberLabelFr);
				fileFieldbusinessApprovalRegistrationNumberClient.setLabelEn(businessApprovalRegistrationNumberLabelEn);
				final FileFieldValue businessApprovalRegistrationNumberClient = new FileFieldValue();
				businessApprovalRegistrationNumberClient.setValue(selectedFileItem.getFile().getClient()
						.getBusinessApprovalRegistrationNumber());
				businessApprovalRegistrationNumberClient.setFileField(fileFieldbusinessApprovalRegistrationNumberClient);
				fieldValues.add(0, businessApprovalRegistrationNumberClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getBusinessApprovalDate()))
			{
				final String businessApprovalDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_businessApprovalDate");
				final String businessApprovalDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_businessApprovalDate");
				final FileField fileFieldbusinessApprovalDateClient = new FileField();
				fileFieldbusinessApprovalDateClient.setLabelFr(businessApprovalDateLabelFr);
				fileFieldbusinessApprovalDateClient.setLabelEn(businessApprovalDateLabelEn);
				final FileFieldValue businessApprovalDateClient = new FileFieldValue();
				businessApprovalDateClient.setValue(selectedFileItem.getFile().getClient().getBusinessApprovalDate());
				businessApprovalDateClient.setFileField(fileFieldbusinessApprovalDateClient);
				fieldValues.add(0, businessApprovalDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getBusinessApprovalValidityDate()))
			{
				final String businessApprovalValidityDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_businessApprovalValidityDate");
				final String businessApprovalValidityDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_businessApprovalValidityDate");
				final FileField fileFieldbusinessApprovalValidityDateClient = new FileField();
				fileFieldbusinessApprovalValidityDateClient.setLabelFr(businessApprovalValidityDateLabelFr);
				fileFieldbusinessApprovalValidityDateClient.setLabelEn(businessApprovalValidityDateLabelEn);
				final FileFieldValue businessApprovalValidityDateClient = new FileFieldValue();
				businessApprovalValidityDateClient.setValue(selectedFileItem.getFile().getClient().getBusinessApprovalValidityDate());
				businessApprovalValidityDateClient.setFileField(fileFieldbusinessApprovalValidityDateClient);
				fieldValues.add(0, businessApprovalValidityDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCommerceApprovalRegistrationNumberFile()))
			{
				final String commerceApprovalRegistrationNumberFileLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME,
						Locale.FRANCE).getString("GeneralInformationLabel_commerceApprovalRegistrationNumberFile");
				final String commerceApprovalRegistrationNumberFileLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME,
						Locale.ENGLISH).getString("GeneralInformationLabel_commerceApprovalRegistrationNumberFile");
				final FileField fileFieldcommerceApprovalRegistrationNumberFileClient = new FileField();
				fileFieldcommerceApprovalRegistrationNumberFileClient.setLabelFr(commerceApprovalRegistrationNumberFileLabelFr);
				fileFieldcommerceApprovalRegistrationNumberFileClient.setLabelEn(commerceApprovalRegistrationNumberFileLabelEn);
				final FileFieldValue commerceApprovalRegistrationNumberFileClient = new FileFieldValue();
				commerceApprovalRegistrationNumberFileClient.setValue(selectedFileItem.getFile().getClient()
						.getCommerceApprovalRegistrationNumberFile());
				commerceApprovalRegistrationNumberFileClient.setFileField(fileFieldcommerceApprovalRegistrationNumberFileClient);
				fieldValues.add(0, commerceApprovalRegistrationNumberFileClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCommerceApprovalObtainedDate()))
			{
				final String commerceApprovalObtainedDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_commerceApprovalObtainedDate");
				final String commerceApprovalObtainedDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_commerceApprovalObtainedDate");
				final FileField fileFieldcommerceApprovalObtainedDateClient = new FileField();
				fileFieldcommerceApprovalObtainedDateClient.setLabelFr(commerceApprovalObtainedDateLabelFr);
				fileFieldcommerceApprovalObtainedDateClient.setLabelEn(commerceApprovalObtainedDateLabelEn);
				final FileFieldValue commerceApprovalObtainedDateClient = new FileFieldValue();
				commerceApprovalObtainedDateClient.setValue(selectedFileItem.getFile().getClient().getCommerceApprovalObtainedDate());
				commerceApprovalObtainedDateClient.setFileField(fileFieldcommerceApprovalObtainedDateClient);
				fieldValues.add(0, commerceApprovalObtainedDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCommerceApprovalValidityDate()))
			{
				final String commerceApprovalValidityDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_commerceApprovalValidityDate");
				final String commerceApprovalValidityDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_commerceApprovalValidityDate");
				final FileField fileFieldcommerceApprovalValidityDateLabelEnClient = new FileField();
				fileFieldcommerceApprovalValidityDateLabelEnClient.setLabelFr(commerceApprovalValidityDateLabelFr);
				fileFieldcommerceApprovalValidityDateLabelEnClient.setLabelEn(commerceApprovalValidityDateLabelEn);
				final FileFieldValue commerceApprovalValidityDateClient = new FileFieldValue();
				commerceApprovalValidityDateClient.setValue(selectedFileItem.getFile().getClient().getCommerceApprovalValidityDate());
				commerceApprovalValidityDateClient.setFileField(fileFieldcommerceApprovalValidityDateLabelEnClient);
				fieldValues.add(0, commerceApprovalValidityDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getCommerceApprovalNumberTraderMap()))
			{
				final String commerceApprovalNumberTraderMapLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE)
						.getString("GeneralInformationLabel_commerceApprovalNumberTraderMap");
				final String commerceApprovalNumberTraderMapLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH)
						.getString("GeneralInformationLabel_commerceApprovalNumberTraderMap");
				final FileField fileFieldcommerceApprovalNumberTraderMapClient = new FileField();
				fileFieldcommerceApprovalNumberTraderMapClient.setLabelFr(commerceApprovalNumberTraderMapLabelFr);
				fileFieldcommerceApprovalNumberTraderMapClient.setLabelEn(commerceApprovalNumberTraderMapLabelEn);
				final FileFieldValue commerceApprovalNumberTraderMapClient = new FileFieldValue();
				commerceApprovalNumberTraderMapClient.setValue(selectedFileItem.getFile().getClient()
						.getCommerceApprovalNumberTraderMap());
				commerceApprovalNumberTraderMapClient.setFileField(fileFieldcommerceApprovalNumberTraderMapClient);
				fieldValues.add(0, commerceApprovalNumberTraderMapClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPermitNumber()))
			{
				final String permitNumberLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_permitNumber");
				final String permitNumberLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_permitNumber");
				final FileField fileFieldpermitNumberClient = new FileField();
				fileFieldpermitNumberClient.setLabelFr(permitNumberLabelFr);
				fileFieldpermitNumberClient.setLabelEn(permitNumberLabelEn);
				final FileFieldValue permitNumberClient = new FileFieldValue();
				permitNumberClient.setValue(selectedFileItem.getFile().getClient().getPermitNumber());
				permitNumberClient.setFileField(fileFieldpermitNumberClient);
				fieldValues.add(0, permitNumberClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPermitObtainingDate()))
			{
				final String permitObtainingDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_permitObtainingDate");
				final String permitObtainingDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_permitObtainingDate");
				final FileField fileFieldpermitObtainingDateClient = new FileField();
				fileFieldpermitObtainingDateClient.setLabelFr(permitObtainingDateLabelFr);
				fileFieldpermitObtainingDateClient.setLabelEn(permitObtainingDateLabelEn);
				final FileFieldValue permitObtainingDateClient = new FileFieldValue();
				permitObtainingDateClient.setValue(selectedFileItem.getFile().getClient().getPermitObtainingDate());
				permitObtainingDateClient.setFileField(fileFieldpermitObtainingDateClient);
				fieldValues.add(0, permitObtainingDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPermitApprovalDate()))
			{
				final String permitApprovalDateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_permitApprovalDate");
				final String permitApprovalDateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_permitApprovalDate");
				final FileField fileFieldpermitApprovalDateClient = new FileField();
				fileFieldpermitApprovalDateClient.setLabelFr(permitApprovalDateLabelFr);
				fileFieldpermitApprovalDateClient.setLabelEn(permitApprovalDateLabelEn);
				final FileFieldValue permitApprovalDateClient = new FileFieldValue();
				permitApprovalDateClient.setValue(selectedFileItem.getFile().getClient().getPermitApprovalDate());
				permitApprovalDateClient.setFileField(fileFieldpermitApprovalDateClient);
				fieldValues.add(0, permitApprovalDateClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getPermitType()))
			{
				final String permitTypeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_permitType");
				final String permitTypeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_permitType");
				final FileField fileFieldpermitTypeClient = new FileField();
				fileFieldpermitTypeClient.setLabelFr(permitTypeLabelFr);
				fileFieldpermitTypeClient.setLabelEn(permitTypeLabelEn);
				final FileFieldValue permitTypeClient = new FileFieldValue();
				permitTypeClient.setValue(selectedFileItem.getFile().getClient().getPermitType());
				permitTypeClient.setFileField(fileFieldpermitTypeClient);
				fieldValues.add(0, permitTypeClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getProfession()))
			{
				final String professionLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_profession");
				final String permitTypeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_profession");
				final FileField fileFieldprofessionClient = new FileField();
				fileFieldprofessionClient.setLabelFr(professionLabelFr);
				fileFieldprofessionClient.setLabelEn(permitTypeLabelEn);
				final FileFieldValue professionClient = new FileFieldValue();
				professionClient.setValue(selectedFileItem.getFile().getClient().getProfession());
				professionClient.setFileField(fileFieldprofessionClient);
				fieldValues.add(0, professionClient);
			}
			if (StringUtils.isNotBlank(selectedFileItem.getFile().getClient().getcNI()))
			{
				final String cniLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
						"GeneralInformationLabel_cni");
				final String cniLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
						"GeneralInformationLabel_cni");
				final FileField fileFieldcniClient = new FileField();
				fileFieldcniClient.setLabelFr(cniLabelFr);
				fileFieldcniClient.setLabelEn(cniLabelEn);
				final FileFieldValue cniClient = new FileFieldValue();
				cniClient.setValue(selectedFileItem.getFile().getClient().getcNI());
				cniClient.setFileField(fileFieldcniClient);
				fieldValues.add(0, cniClient);
			}
		}

		// File.Service
		if (selectedFileItem.getFile().getBureau() != null
				&& selectedFileItem.getFile().getBureau().getService().getLabelEn() != null)
		{
			final String serviceLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
					"GeneralInformationLabel_Service");
			final String serviceLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
					"GeneralInformationLabel_Service");
			final FileField fileFieldService = new FileField();
			fileFieldService.setLabelFr(serviceLabelFr);
			fileFieldService.setLabelEn(serviceLabelEn);
			final FileFieldValue service = new FileFieldValue();
			service.setValue(RepetableUtil.locale.equals(Locale.FRANCE) ? selectedFileItem.getFile().getBureau().getService()
					.getLabelFr() : selectedFileItem.getFile().getBureau().getService().getLabelEn());
			service.setFileField(fileFieldService);
			fieldValues.add(0, service);
		}

		// File.FileType
		if (selectedFileItem.getFile().getFileType() != null)
		{
			final String natureLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
					"GeneralInformationLabel_nature");
			final String natureLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
					"GeneralInformationLabel_nature");
			final FileField fileFieldFileType = new FileField();
			fileFieldFileType.setLabelFr(natureLabelFr);
			fileFieldFileType.setLabelEn(natureLabelEn);
			final FileFieldValue fileType = new FileFieldValue();
			fileType.setValue(RepetableUtil.locale.equals(Locale.FRANCE) ? selectedFileItem.getFile().getFileType().getLabelFr()
					: selectedFileItem.getFile().getFileType().getLabelEn());
			fileType.setFileField(fileFieldFileType);
			fieldValues.add(0, fileType);
		}

		// File.CreatedDate
		final String dateLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
				"GeneralInformationLabel_date");
		final String dateLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
				"GeneralInformationLabel_date");
		final FileField fileFieldCreateDate = new FileField();
		fileFieldCreateDate.setLabelFr(dateLabelFr);
		fileFieldCreateDate.setLabelEn(dateLabelEn);
		final FileFieldValue createDate = new FileFieldValue();
		createDate.setValue(new java.text.SimpleDateFormat("dd/MM/yyyy").format(selectedFileItem.getFile().getCreatedDate()));
		createDate.setFileField(fileFieldCreateDate);
		fieldValues.add(0, createDate);

		// File.ReferenceSiat
		final String numSiatLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
				"GeneralInformationLabel_numSiat");
		final String numSiatLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
				"GeneralInformationLabel_numSiat");
		final FileField fileFieldReferenceSiat = new FileField();
		fileFieldReferenceSiat.setLabelFr(numSiatLabelFr);
		fileFieldReferenceSiat.setLabelEn(numSiatLabelEn);
		final FileFieldValue referenceSiat = new FileFieldValue();
		referenceSiat.setValue(selectedFileItem.getFile().getReferenceSiat());
		referenceSiat.setFileField(fileFieldReferenceSiat);
		fieldValues.add(0, referenceSiat);

		// File.NumeroDemande
		final String numDemandeLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
				"GeneralInformationLabel_numDemande");
		final String numDemandeLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
				"GeneralInformationLabel_numDemande");
		final FileField fileFieldNumeroDemande = new FileField();
		fileFieldNumeroDemande.setLabelFr(numDemandeLabelFr);
		fileFieldNumeroDemande.setLabelEn(numDemandeLabelEn);
		final FileFieldValue numeroDemande = new FileFieldValue();
		numeroDemande.setValue(selectedFileItem.getFile().getNumeroDemande());
		numeroDemande.setFileField(fileFieldNumeroDemande);
		fieldValues.add(0, numeroDemande);

		// File.NumeroDossier
		final String referenceGuceLabelFr = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.FRANCE).getString(
				"GeneralInformationLabel_numGuce");
		final String referenceGuceLabelEn = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, Locale.ENGLISH).getString(
				"GeneralInformationLabel_numGuce");
		final FileField fileFieldReferenceGuce = new FileField();
		fileFieldReferenceGuce.setLabelFr(referenceGuceLabelFr);
		fileFieldReferenceGuce.setLabelEn(referenceGuceLabelEn);
		final FileFieldValue referenceGuce = new FileFieldValue();
		referenceGuce.setValue(selectedFileItem.getFile().getNumeroDossier());
		referenceGuce.setFileField(fileFieldReferenceGuce);
		fieldValues.add(0, referenceGuce);

		fileFieldGroupDto.setFieldValues(fieldValues);
	}

}
