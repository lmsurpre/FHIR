/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import com.ibm.watsonhealth.fhir.model.annotation.Constraint;
import com.ibm.watsonhealth.fhir.model.type.BackboneElement;
import com.ibm.watsonhealth.fhir.model.type.Boolean;
import com.ibm.watsonhealth.fhir.model.type.Canonical;
import com.ibm.watsonhealth.fhir.model.type.Code;
import com.ibm.watsonhealth.fhir.model.type.CodeableConcept;
import com.ibm.watsonhealth.fhir.model.type.ContactDetail;
import com.ibm.watsonhealth.fhir.model.type.Date;
import com.ibm.watsonhealth.fhir.model.type.DateTime;
import com.ibm.watsonhealth.fhir.model.type.Element;
import com.ibm.watsonhealth.fhir.model.type.Expression;
import com.ibm.watsonhealth.fhir.model.type.Extension;
import com.ibm.watsonhealth.fhir.model.type.Id;
import com.ibm.watsonhealth.fhir.model.type.Identifier;
import com.ibm.watsonhealth.fhir.model.type.Markdown;
import com.ibm.watsonhealth.fhir.model.type.Meta;
import com.ibm.watsonhealth.fhir.model.type.Narrative;
import com.ibm.watsonhealth.fhir.model.type.Period;
import com.ibm.watsonhealth.fhir.model.type.PublicationStatus;
import com.ibm.watsonhealth.fhir.model.type.Reference;
import com.ibm.watsonhealth.fhir.model.type.RelatedArtifact;
import com.ibm.watsonhealth.fhir.model.type.String;
import com.ibm.watsonhealth.fhir.model.type.Uri;
import com.ibm.watsonhealth.fhir.model.type.UsageContext;
import com.ibm.watsonhealth.fhir.model.util.ValidationSupport;
import com.ibm.watsonhealth.fhir.model.visitor.Visitor;

/**
 * <p>
 * The Measure resource provides the definition of a quality measure.
 * </p>
 */
@Constraint(
    id = "mea-0",
    level = "Warning",
    location = "(base)",
    description = "Name should be usable as an identifier for the module by machine processing applications such as code generation",
    expression = "name.matches('[A-Z]([A-Za-z0-9_]){0,254}')"
)
@Constraint(
    id = "mea-1",
    level = "Rule",
    location = "(base)",
    description = "Stratifier SHALL be either a single criteria or a set of criteria components",
    expression = "group.stratifier.all((code | description | criteria).exists() xor component.exists())"
)
@Generated("com.ibm.watsonhealth.fhir.tools.CodeGenerator")
public class Measure extends DomainResource {
    private final Uri url;
    private final List<Identifier> identifier;
    private final String version;
    private final String name;
    private final String title;
    private final String subtitle;
    private final PublicationStatus status;
    private final Boolean experimental;
    private final Element subject;
    private final DateTime date;
    private final String publisher;
    private final List<ContactDetail> contact;
    private final Markdown description;
    private final List<UsageContext> useContext;
    private final List<CodeableConcept> jurisdiction;
    private final Markdown purpose;
    private final String usage;
    private final Markdown copyright;
    private final Date approvalDate;
    private final Date lastReviewDate;
    private final Period effectivePeriod;
    private final List<CodeableConcept> topic;
    private final List<ContactDetail> author;
    private final List<ContactDetail> editor;
    private final List<ContactDetail> reviewer;
    private final List<ContactDetail> endorser;
    private final List<RelatedArtifact> relatedArtifact;
    private final List<Canonical> library;
    private final Markdown disclaimer;
    private final CodeableConcept scoring;
    private final CodeableConcept compositeScoring;
    private final List<CodeableConcept> type;
    private final String riskAdjustment;
    private final String rateAggregation;
    private final Markdown rationale;
    private final Markdown clinicalRecommendationStatement;
    private final CodeableConcept improvementNotation;
    private final List<Markdown> definition;
    private final Markdown guidance;
    private final List<Group> group;
    private final List<SupplementalData> supplementalData;

    private Measure(Builder builder) {
        super(builder);
        url = builder.url;
        identifier = Collections.unmodifiableList(builder.identifier);
        version = builder.version;
        name = builder.name;
        title = builder.title;
        subtitle = builder.subtitle;
        status = ValidationSupport.requireNonNull(builder.status, "status");
        experimental = builder.experimental;
        subject = ValidationSupport.choiceElement(builder.subject, "subject", CodeableConcept.class, Reference.class);
        date = builder.date;
        publisher = builder.publisher;
        contact = Collections.unmodifiableList(builder.contact);
        description = builder.description;
        useContext = Collections.unmodifiableList(builder.useContext);
        jurisdiction = Collections.unmodifiableList(builder.jurisdiction);
        purpose = builder.purpose;
        usage = builder.usage;
        copyright = builder.copyright;
        approvalDate = builder.approvalDate;
        lastReviewDate = builder.lastReviewDate;
        effectivePeriod = builder.effectivePeriod;
        topic = Collections.unmodifiableList(builder.topic);
        author = Collections.unmodifiableList(builder.author);
        editor = Collections.unmodifiableList(builder.editor);
        reviewer = Collections.unmodifiableList(builder.reviewer);
        endorser = Collections.unmodifiableList(builder.endorser);
        relatedArtifact = Collections.unmodifiableList(builder.relatedArtifact);
        library = Collections.unmodifiableList(builder.library);
        disclaimer = builder.disclaimer;
        scoring = builder.scoring;
        compositeScoring = builder.compositeScoring;
        type = Collections.unmodifiableList(builder.type);
        riskAdjustment = builder.riskAdjustment;
        rateAggregation = builder.rateAggregation;
        rationale = builder.rationale;
        clinicalRecommendationStatement = builder.clinicalRecommendationStatement;
        improvementNotation = builder.improvementNotation;
        definition = Collections.unmodifiableList(builder.definition);
        guidance = builder.guidance;
        group = Collections.unmodifiableList(builder.group);
        supplementalData = Collections.unmodifiableList(builder.supplementalData);
    }

    /**
     * <p>
     * An absolute URI that is used to identify this measure when it is referenced in a specification, model, design or an 
     * instance; also called its canonical identifier. This SHOULD be globally unique and SHOULD be a literal address at 
     * which at which an authoritative instance of this measure is (or will be) published. This URL can be the target of a 
     * canonical reference. It SHALL remain the same when the measure is stored on different servers.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Uri}.
     */
    public Uri getUrl() {
        return url;
    }

    /**
     * <p>
     * A formal identifier that is used to identify this measure when it is represented in other formats, or referenced in a 
     * specification, model, design or an instance.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Identifier}.
     */
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    /**
     * <p>
     * The identifier that is used to identify this version of the measure when it is referenced in a specification, model, 
     * design or instance. This is an arbitrary value managed by the measure author and is not expected to be globally 
     * unique. For example, it might be a timestamp (e.g. yyyymmdd) if a managed version is not available. There is also no 
     * expectation that versions can be placed in a lexicographical sequence. To provide a version consistent with the 
     * Decision Support Service specification, use the format Major.Minor.Revision (e.g. 1.0.0). For more information on 
     * versioning knowledge assets, refer to the Decision Support Service specification. Note that a version is required for 
     * non-experimental active artifacts.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>
     * A natural language name identifying the measure. This name should be usable as an identifier for the module by machine 
     * processing applications such as code generation.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * A short, descriptive, user-friendly title for the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * An explanatory or alternate title for the measure giving additional information about its content.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * <p>
     * The status of this measure. Enables tracking the life-cycle of the content.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link PublicationStatus}.
     */
    public PublicationStatus getStatus() {
        return status;
    }

    /**
     * <p>
     * A Boolean value to indicate that this measure is authored for testing purposes (or education/evaluation/marketing) and 
     * is not intended to be used for genuine usage.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Boolean}.
     */
    public Boolean getExperimental() {
        return experimental;
    }

    /**
     * <p>
     * The intended subjects for the measure. If this element is not provided, a Patient subject is assumed, but the subject 
     * of the measure can be anything.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Element}.
     */
    public Element getSubject() {
        return subject;
    }

    /**
     * <p>
     * The date (and optionally time) when the measure was published. The date must change when the business version changes 
     * and it must change if the status code changes. In addition, it should change when the substantive content of the 
     * measure changes.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link DateTime}.
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * <p>
     * The name of the organization or individual that published the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * <p>
     * Contact details to assist a user in finding and communicating with the publisher.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getContact() {
        return contact;
    }

    /**
     * <p>
     * A free text natural language description of the measure from a consumer's perspective.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getDescription() {
        return description;
    }

    /**
     * <p>
     * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
     * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
     * may be used to assist with indexing and searching for appropriate measure instances.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link UsageContext}.
     */
    public List<UsageContext> getUseContext() {
        return useContext;
    }

    /**
     * <p>
     * A legal or geographic region in which the measure is intended to be used.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link CodeableConcept}.
     */
    public List<CodeableConcept> getJurisdiction() {
        return jurisdiction;
    }

    /**
     * <p>
     * Explanation of why this measure is needed and why it has been designed as it has.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getPurpose() {
        return purpose;
    }

    /**
     * <p>
     * A detailed description, from a clinical perspective, of how the measure is used.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * <p>
     * A copyright statement relating to the measure and/or its contents. Copyright statements are generally legal 
     * restrictions on the use and publishing of the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getCopyright() {
        return copyright;
    }

    /**
     * <p>
     * The date on which the resource content was approved by the publisher. Approval happens once when the content is 
     * officially approved for usage.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Date}.
     */
    public Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * <p>
     * The date on which the resource content was last reviewed. Review happens periodically after approval but does not 
     * change the original approval date.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Date}.
     */
    public Date getLastReviewDate() {
        return lastReviewDate;
    }

    /**
     * <p>
     * The period during which the measure content was or is planned to be in active use.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Period}.
     */
    public Period getEffectivePeriod() {
        return effectivePeriod;
    }

    /**
     * <p>
     * Descriptive topics related to the content of the measure. Topics provide a high-level categorization grouping types of 
     * measures that can be useful for filtering and searching.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link CodeableConcept}.
     */
    public List<CodeableConcept> getTopic() {
        return topic;
    }

    /**
     * <p>
     * An individiual or organization primarily involved in the creation and maintenance of the content.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getAuthor() {
        return author;
    }

    /**
     * <p>
     * An individual or organization primarily responsible for internal coherence of the content.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getEditor() {
        return editor;
    }

    /**
     * <p>
     * An individual or organization primarily responsible for review of some aspect of the content.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getReviewer() {
        return reviewer;
    }

    /**
     * <p>
     * An individual or organization responsible for officially endorsing the content for use in some setting.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getEndorser() {
        return endorser;
    }

    /**
     * <p>
     * Related artifacts such as additional documentation, justification, or bibliographic references.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link RelatedArtifact}.
     */
    public List<RelatedArtifact> getRelatedArtifact() {
        return relatedArtifact;
    }

    /**
     * <p>
     * A reference to a Library resource containing the formal logic used by the measure.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Canonical}.
     */
    public List<Canonical> getLibrary() {
        return library;
    }

    /**
     * <p>
     * Notices and disclaimers regarding the use of the measure or related to intellectual property (such as code systems) 
     * referenced by the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getDisclaimer() {
        return disclaimer;
    }

    /**
     * <p>
     * Indicates how the calculation is performed for the measure, including proportion, ratio, continuous-variable, and 
     * cohort. The value set is extensible, allowing additional measure scoring types to be represented.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link CodeableConcept}.
     */
    public CodeableConcept getScoring() {
        return scoring;
    }

    /**
     * <p>
     * If this is a composite measure, the scoring method used to combine the component measures to determine the composite 
     * score.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link CodeableConcept}.
     */
    public CodeableConcept getCompositeScoring() {
        return compositeScoring;
    }

    /**
     * <p>
     * Indicates whether the measure is used to examine a process, an outcome over time, a patient-reported outcome, or a 
     * structure measure such as utilization.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link CodeableConcept}.
     */
    public List<CodeableConcept> getType() {
        return type;
    }

    /**
     * <p>
     * A description of the risk adjustment factors that may impact the resulting score for the measure and how they may be 
     * accounted for when computing and reporting measure results.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getRiskAdjustment() {
        return riskAdjustment;
    }

    /**
     * <p>
     * Describes how to combine the information calculated, based on logic in each of several populations, into one 
     * summarized result.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getRateAggregation() {
        return rateAggregation;
    }

    /**
     * <p>
     * Provides a succinct statement of the need for the measure. Usually includes statements pertaining to importance 
     * criterion: impact, gap in care, and evidence.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getRationale() {
        return rationale;
    }

    /**
     * <p>
     * Provides a summary of relevant clinical guidelines or other clinical recommendations supporting the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getClinicalRecommendationStatement() {
        return clinicalRecommendationStatement;
    }

    /**
     * <p>
     * Information on whether an increase or decrease in score is the preferred result (e.g., a higher score indicates better 
     * quality OR a lower score indicates better quality OR quality is within a range).
     * </p>
     * 
     * @return
     *     An immutable object of type {@link CodeableConcept}.
     */
    public CodeableConcept getImprovementNotation() {
        return improvementNotation;
    }

    /**
     * <p>
     * Provides a description of an individual term used within the measure.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Markdown}.
     */
    public List<Markdown> getDefinition() {
        return definition;
    }

    /**
     * <p>
     * Additional guidance for the measure including how it can be used in a clinical context, and the intent of the measure.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getGuidance() {
        return guidance;
    }

    /**
     * <p>
     * A group of population criteria for the measure.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Group}.
     */
    public List<Group> getGroup() {
        return group;
    }

    /**
     * <p>
     * The supplemental data criteria for the measure report, specified as either the name of a valid CQL expression within a 
     * referenced library, or a valid FHIR Resource Path.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link SupplementalData}.
     */
    public List<SupplementalData> getSupplementalData() {
        return supplementalData;
    }

    @Override
    public void accept(java.lang.String elementName, Visitor visitor) {
        if (visitor.preVisit(this)) {
            visitor.visitStart(elementName, this);
            if (visitor.visit(elementName, this)) {
                // visit children
                accept(id, "id", visitor);
                accept(meta, "meta", visitor);
                accept(implicitRules, "implicitRules", visitor);
                accept(language, "language", visitor);
                accept(text, "text", visitor);
                accept(contained, "contained", visitor, Resource.class);
                accept(extension, "extension", visitor, Extension.class);
                accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                accept(url, "url", visitor);
                accept(identifier, "identifier", visitor, Identifier.class);
                accept(version, "version", visitor);
                accept(name, "name", visitor);
                accept(title, "title", visitor);
                accept(subtitle, "subtitle", visitor);
                accept(status, "status", visitor);
                accept(experimental, "experimental", visitor);
                accept(subject, "subject", visitor, true);
                accept(date, "date", visitor);
                accept(publisher, "publisher", visitor);
                accept(contact, "contact", visitor, ContactDetail.class);
                accept(description, "description", visitor);
                accept(useContext, "useContext", visitor, UsageContext.class);
                accept(jurisdiction, "jurisdiction", visitor, CodeableConcept.class);
                accept(purpose, "purpose", visitor);
                accept(usage, "usage", visitor);
                accept(copyright, "copyright", visitor);
                accept(approvalDate, "approvalDate", visitor);
                accept(lastReviewDate, "lastReviewDate", visitor);
                accept(effectivePeriod, "effectivePeriod", visitor);
                accept(topic, "topic", visitor, CodeableConcept.class);
                accept(author, "author", visitor, ContactDetail.class);
                accept(editor, "editor", visitor, ContactDetail.class);
                accept(reviewer, "reviewer", visitor, ContactDetail.class);
                accept(endorser, "endorser", visitor, ContactDetail.class);
                accept(relatedArtifact, "relatedArtifact", visitor, RelatedArtifact.class);
                accept(library, "library", visitor, Canonical.class);
                accept(disclaimer, "disclaimer", visitor);
                accept(scoring, "scoring", visitor);
                accept(compositeScoring, "compositeScoring", visitor);
                accept(type, "type", visitor, CodeableConcept.class);
                accept(riskAdjustment, "riskAdjustment", visitor);
                accept(rateAggregation, "rateAggregation", visitor);
                accept(rationale, "rationale", visitor);
                accept(clinicalRecommendationStatement, "clinicalRecommendationStatement", visitor);
                accept(improvementNotation, "improvementNotation", visitor);
                accept(definition, "definition", visitor, Markdown.class);
                accept(guidance, "guidance", visitor);
                accept(group, "group", visitor, Group.class);
                accept(supplementalData, "supplementalData", visitor, SupplementalData.class);
            }
            visitor.visitEnd(elementName, this);
            visitor.postVisit(this);
        }
    }

    @Override
    public Builder toBuilder() {
        return new Builder(status).from(this);
    }

    public Builder toBuilder(PublicationStatus status) {
        return new Builder(status).from(this);
    }

    public static Builder builder(PublicationStatus status) {
        return new Builder(status);
    }

    public static class Builder extends DomainResource.Builder {
        // required
        private final PublicationStatus status;

        // optional
        private Uri url;
        private List<Identifier> identifier = new ArrayList<>();
        private String version;
        private String name;
        private String title;
        private String subtitle;
        private Boolean experimental;
        private Element subject;
        private DateTime date;
        private String publisher;
        private List<ContactDetail> contact = new ArrayList<>();
        private Markdown description;
        private List<UsageContext> useContext = new ArrayList<>();
        private List<CodeableConcept> jurisdiction = new ArrayList<>();
        private Markdown purpose;
        private String usage;
        private Markdown copyright;
        private Date approvalDate;
        private Date lastReviewDate;
        private Period effectivePeriod;
        private List<CodeableConcept> topic = new ArrayList<>();
        private List<ContactDetail> author = new ArrayList<>();
        private List<ContactDetail> editor = new ArrayList<>();
        private List<ContactDetail> reviewer = new ArrayList<>();
        private List<ContactDetail> endorser = new ArrayList<>();
        private List<RelatedArtifact> relatedArtifact = new ArrayList<>();
        private List<Canonical> library = new ArrayList<>();
        private Markdown disclaimer;
        private CodeableConcept scoring;
        private CodeableConcept compositeScoring;
        private List<CodeableConcept> type = new ArrayList<>();
        private String riskAdjustment;
        private String rateAggregation;
        private Markdown rationale;
        private Markdown clinicalRecommendationStatement;
        private CodeableConcept improvementNotation;
        private List<Markdown> definition = new ArrayList<>();
        private Markdown guidance;
        private List<Group> group = new ArrayList<>();
        private List<SupplementalData> supplementalData = new ArrayList<>();

        private Builder(PublicationStatus status) {
            super();
            this.status = status;
        }

        /**
         * <p>
         * The logical id of the resource, as used in the URL for the resource. Once assigned, this value never changes.
         * </p>
         * 
         * @param id
         *     Logical id of this artifact
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder id(Id id) {
            return (Builder) super.id(id);
        }

        /**
         * <p>
         * The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content 
         * might not always be associated with version changes to the resource.
         * </p>
         * 
         * @param meta
         *     Metadata about the resource
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder meta(Meta meta) {
            return (Builder) super.meta(meta);
        }

        /**
         * <p>
         * A reference to a set of rules that were followed when the resource was constructed, and which must be understood when 
         * processing the content. Often, this is a reference to an implementation guide that defines the special rules along 
         * with other profiles etc.
         * </p>
         * 
         * @param implicitRules
         *     A set of rules under which this content was created
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder implicitRules(Uri implicitRules) {
            return (Builder) super.implicitRules(implicitRules);
        }

        /**
         * <p>
         * The base language in which the resource is written.
         * </p>
         * 
         * @param language
         *     Language of the resource content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder language(Code language) {
            return (Builder) super.language(language);
        }

        /**
         * <p>
         * A human-readable narrative that contains a summary of the resource and can be used to represent the content of the 
         * resource to a human. The narrative need not encode all the structured data, but is required to contain sufficient 
         * detail to make it "clinically safe" for a human to just read the narrative. Resource definitions may define what 
         * content should be represented in the narrative to ensure clinical safety.
         * </p>
         * 
         * @param text
         *     Text summary of the resource, for human interpretation
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder text(Narrative text) {
            return (Builder) super.text(text);
        }

        /**
         * <p>
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * </p>
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder contained(Resource... contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * <p>
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * </p>
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder contained(Collection<Resource> contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * </p>
         * <p>
         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * </p>
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder modifierExtension(Extension... modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * </p>
         * <p>
         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * </p>
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder modifierExtension(Collection<Extension> modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * <p>
         * An absolute URI that is used to identify this measure when it is referenced in a specification, model, design or an 
         * instance; also called its canonical identifier. This SHOULD be globally unique and SHOULD be a literal address at 
         * which at which an authoritative instance of this measure is (or will be) published. This URL can be the target of a 
         * canonical reference. It SHALL remain the same when the measure is stored on different servers.
         * </p>
         * 
         * @param url
         *     Canonical identifier for this measure, represented as a URI (globally unique)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder url(Uri url) {
            this.url = url;
            return this;
        }

        /**
         * <p>
         * A formal identifier that is used to identify this measure when it is represented in other formats, or referenced in a 
         * specification, model, design or an instance.
         * </p>
         * 
         * @param identifier
         *     Additional identifier for the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder identifier(Identifier... identifier) {
            for (Identifier value : identifier) {
                this.identifier.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A formal identifier that is used to identify this measure when it is represented in other formats, or referenced in a 
         * specification, model, design or an instance.
         * </p>
         * 
         * @param identifier
         *     Additional identifier for the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder identifier(Collection<Identifier> identifier) {
            this.identifier.addAll(identifier);
            return this;
        }

        /**
         * <p>
         * The identifier that is used to identify this version of the measure when it is referenced in a specification, model, 
         * design or instance. This is an arbitrary value managed by the measure author and is not expected to be globally 
         * unique. For example, it might be a timestamp (e.g. yyyymmdd) if a managed version is not available. There is also no 
         * expectation that versions can be placed in a lexicographical sequence. To provide a version consistent with the 
         * Decision Support Service specification, use the format Major.Minor.Revision (e.g. 1.0.0). For more information on 
         * versioning knowledge assets, refer to the Decision Support Service specification. Note that a version is required for 
         * non-experimental active artifacts.
         * </p>
         * 
         * @param version
         *     Business version of the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        /**
         * <p>
         * A natural language name identifying the measure. This name should be usable as an identifier for the module by machine 
         * processing applications such as code generation.
         * </p>
         * 
         * @param name
         *     Name for this measure (computer friendly)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <p>
         * A short, descriptive, user-friendly title for the measure.
         * </p>
         * 
         * @param title
         *     Name for this measure (human friendly)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * <p>
         * An explanatory or alternate title for the measure giving additional information about its content.
         * </p>
         * 
         * @param subtitle
         *     Subordinate title of the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        /**
         * <p>
         * A Boolean value to indicate that this measure is authored for testing purposes (or education/evaluation/marketing) and 
         * is not intended to be used for genuine usage.
         * </p>
         * 
         * @param experimental
         *     For testing purposes, not real usage
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder experimental(Boolean experimental) {
            this.experimental = experimental;
            return this;
        }

        /**
         * <p>
         * The intended subjects for the measure. If this element is not provided, a Patient subject is assumed, but the subject 
         * of the measure can be anything.
         * </p>
         * 
         * @param subject
         *     E.g. Patient, Practitioner, RelatedPerson, Organization, Location, Device
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder subject(Element subject) {
            this.subject = subject;
            return this;
        }

        /**
         * <p>
         * The date (and optionally time) when the measure was published. The date must change when the business version changes 
         * and it must change if the status code changes. In addition, it should change when the substantive content of the 
         * measure changes.
         * </p>
         * 
         * @param date
         *     Date last changed
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder date(DateTime date) {
            this.date = date;
            return this;
        }

        /**
         * <p>
         * The name of the organization or individual that published the measure.
         * </p>
         * 
         * @param publisher
         *     Name of the publisher (organization or individual)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        /**
         * <p>
         * Contact details to assist a user in finding and communicating with the publisher.
         * </p>
         * 
         * @param contact
         *     Contact details for the publisher
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder contact(ContactDetail... contact) {
            for (ContactDetail value : contact) {
                this.contact.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Contact details to assist a user in finding and communicating with the publisher.
         * </p>
         * 
         * @param contact
         *     Contact details for the publisher
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder contact(Collection<ContactDetail> contact) {
            this.contact.addAll(contact);
            return this;
        }

        /**
         * <p>
         * A free text natural language description of the measure from a consumer's perspective.
         * </p>
         * 
         * @param description
         *     Natural language description of the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder description(Markdown description) {
            this.description = description;
            return this;
        }

        /**
         * <p>
         * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
         * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
         * may be used to assist with indexing and searching for appropriate measure instances.
         * </p>
         * 
         * @param useContext
         *     The context that the content is intended to support
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder useContext(UsageContext... useContext) {
            for (UsageContext value : useContext) {
                this.useContext.add(value);
            }
            return this;
        }

        /**
         * <p>
         * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
         * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
         * may be used to assist with indexing and searching for appropriate measure instances.
         * </p>
         * 
         * @param useContext
         *     The context that the content is intended to support
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder useContext(Collection<UsageContext> useContext) {
            this.useContext.addAll(useContext);
            return this;
        }

        /**
         * <p>
         * A legal or geographic region in which the measure is intended to be used.
         * </p>
         * 
         * @param jurisdiction
         *     Intended jurisdiction for measure (if applicable)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder jurisdiction(CodeableConcept... jurisdiction) {
            for (CodeableConcept value : jurisdiction) {
                this.jurisdiction.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A legal or geographic region in which the measure is intended to be used.
         * </p>
         * 
         * @param jurisdiction
         *     Intended jurisdiction for measure (if applicable)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder jurisdiction(Collection<CodeableConcept> jurisdiction) {
            this.jurisdiction.addAll(jurisdiction);
            return this;
        }

        /**
         * <p>
         * Explanation of why this measure is needed and why it has been designed as it has.
         * </p>
         * 
         * @param purpose
         *     Why this measure is defined
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder purpose(Markdown purpose) {
            this.purpose = purpose;
            return this;
        }

        /**
         * <p>
         * A detailed description, from a clinical perspective, of how the measure is used.
         * </p>
         * 
         * @param usage
         *     Describes the clinical usage of the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder usage(String usage) {
            this.usage = usage;
            return this;
        }

        /**
         * <p>
         * A copyright statement relating to the measure and/or its contents. Copyright statements are generally legal 
         * restrictions on the use and publishing of the measure.
         * </p>
         * 
         * @param copyright
         *     Use and/or publishing restrictions
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder copyright(Markdown copyright) {
            this.copyright = copyright;
            return this;
        }

        /**
         * <p>
         * The date on which the resource content was approved by the publisher. Approval happens once when the content is 
         * officially approved for usage.
         * </p>
         * 
         * @param approvalDate
         *     When the measure was approved by publisher
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder approvalDate(Date approvalDate) {
            this.approvalDate = approvalDate;
            return this;
        }

        /**
         * <p>
         * The date on which the resource content was last reviewed. Review happens periodically after approval but does not 
         * change the original approval date.
         * </p>
         * 
         * @param lastReviewDate
         *     When the measure was last reviewed
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder lastReviewDate(Date lastReviewDate) {
            this.lastReviewDate = lastReviewDate;
            return this;
        }

        /**
         * <p>
         * The period during which the measure content was or is planned to be in active use.
         * </p>
         * 
         * @param effectivePeriod
         *     When the measure is expected to be used
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder effectivePeriod(Period effectivePeriod) {
            this.effectivePeriod = effectivePeriod;
            return this;
        }

        /**
         * <p>
         * Descriptive topics related to the content of the measure. Topics provide a high-level categorization grouping types of 
         * measures that can be useful for filtering and searching.
         * </p>
         * 
         * @param topic
         *     The category of the measure, such as Education, Treatment, Assessment, etc.
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder topic(CodeableConcept... topic) {
            for (CodeableConcept value : topic) {
                this.topic.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Descriptive topics related to the content of the measure. Topics provide a high-level categorization grouping types of 
         * measures that can be useful for filtering and searching.
         * </p>
         * 
         * @param topic
         *     The category of the measure, such as Education, Treatment, Assessment, etc.
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder topic(Collection<CodeableConcept> topic) {
            this.topic.addAll(topic);
            return this;
        }

        /**
         * <p>
         * An individiual or organization primarily involved in the creation and maintenance of the content.
         * </p>
         * 
         * @param author
         *     Who authored the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder author(ContactDetail... author) {
            for (ContactDetail value : author) {
                this.author.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An individiual or organization primarily involved in the creation and maintenance of the content.
         * </p>
         * 
         * @param author
         *     Who authored the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder author(Collection<ContactDetail> author) {
            this.author.addAll(author);
            return this;
        }

        /**
         * <p>
         * An individual or organization primarily responsible for internal coherence of the content.
         * </p>
         * 
         * @param editor
         *     Who edited the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder editor(ContactDetail... editor) {
            for (ContactDetail value : editor) {
                this.editor.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An individual or organization primarily responsible for internal coherence of the content.
         * </p>
         * 
         * @param editor
         *     Who edited the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder editor(Collection<ContactDetail> editor) {
            this.editor.addAll(editor);
            return this;
        }

        /**
         * <p>
         * An individual or organization primarily responsible for review of some aspect of the content.
         * </p>
         * 
         * @param reviewer
         *     Who reviewed the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder reviewer(ContactDetail... reviewer) {
            for (ContactDetail value : reviewer) {
                this.reviewer.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An individual or organization primarily responsible for review of some aspect of the content.
         * </p>
         * 
         * @param reviewer
         *     Who reviewed the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder reviewer(Collection<ContactDetail> reviewer) {
            this.reviewer.addAll(reviewer);
            return this;
        }

        /**
         * <p>
         * An individual or organization responsible for officially endorsing the content for use in some setting.
         * </p>
         * 
         * @param endorser
         *     Who endorsed the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder endorser(ContactDetail... endorser) {
            for (ContactDetail value : endorser) {
                this.endorser.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An individual or organization responsible for officially endorsing the content for use in some setting.
         * </p>
         * 
         * @param endorser
         *     Who endorsed the content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder endorser(Collection<ContactDetail> endorser) {
            this.endorser.addAll(endorser);
            return this;
        }

        /**
         * <p>
         * Related artifacts such as additional documentation, justification, or bibliographic references.
         * </p>
         * 
         * @param relatedArtifact
         *     Additional documentation, citations, etc.
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder relatedArtifact(RelatedArtifact... relatedArtifact) {
            for (RelatedArtifact value : relatedArtifact) {
                this.relatedArtifact.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Related artifacts such as additional documentation, justification, or bibliographic references.
         * </p>
         * 
         * @param relatedArtifact
         *     Additional documentation, citations, etc.
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder relatedArtifact(Collection<RelatedArtifact> relatedArtifact) {
            this.relatedArtifact.addAll(relatedArtifact);
            return this;
        }

        /**
         * <p>
         * A reference to a Library resource containing the formal logic used by the measure.
         * </p>
         * 
         * @param library
         *     Logic used by the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder library(Canonical... library) {
            for (Canonical value : library) {
                this.library.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A reference to a Library resource containing the formal logic used by the measure.
         * </p>
         * 
         * @param library
         *     Logic used by the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder library(Collection<Canonical> library) {
            this.library.addAll(library);
            return this;
        }

        /**
         * <p>
         * Notices and disclaimers regarding the use of the measure or related to intellectual property (such as code systems) 
         * referenced by the measure.
         * </p>
         * 
         * @param disclaimer
         *     Disclaimer for use of the measure or its referenced content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder disclaimer(Markdown disclaimer) {
            this.disclaimer = disclaimer;
            return this;
        }

        /**
         * <p>
         * Indicates how the calculation is performed for the measure, including proportion, ratio, continuous-variable, and 
         * cohort. The value set is extensible, allowing additional measure scoring types to be represented.
         * </p>
         * 
         * @param scoring
         *     proportion | ratio | continuous-variable | cohort
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder scoring(CodeableConcept scoring) {
            this.scoring = scoring;
            return this;
        }

        /**
         * <p>
         * If this is a composite measure, the scoring method used to combine the component measures to determine the composite 
         * score.
         * </p>
         * 
         * @param compositeScoring
         *     opportunity | all-or-nothing | linear | weighted
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder compositeScoring(CodeableConcept compositeScoring) {
            this.compositeScoring = compositeScoring;
            return this;
        }

        /**
         * <p>
         * Indicates whether the measure is used to examine a process, an outcome over time, a patient-reported outcome, or a 
         * structure measure such as utilization.
         * </p>
         * 
         * @param type
         *     process | outcome | structure | patient-reported-outcome | composite
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder type(CodeableConcept... type) {
            for (CodeableConcept value : type) {
                this.type.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Indicates whether the measure is used to examine a process, an outcome over time, a patient-reported outcome, or a 
         * structure measure such as utilization.
         * </p>
         * 
         * @param type
         *     process | outcome | structure | patient-reported-outcome | composite
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder type(Collection<CodeableConcept> type) {
            this.type.addAll(type);
            return this;
        }

        /**
         * <p>
         * A description of the risk adjustment factors that may impact the resulting score for the measure and how they may be 
         * accounted for when computing and reporting measure results.
         * </p>
         * 
         * @param riskAdjustment
         *     How risk adjustment is applied for this measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder riskAdjustment(String riskAdjustment) {
            this.riskAdjustment = riskAdjustment;
            return this;
        }

        /**
         * <p>
         * Describes how to combine the information calculated, based on logic in each of several populations, into one 
         * summarized result.
         * </p>
         * 
         * @param rateAggregation
         *     How is rate aggregation performed for this measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder rateAggregation(String rateAggregation) {
            this.rateAggregation = rateAggregation;
            return this;
        }

        /**
         * <p>
         * Provides a succinct statement of the need for the measure. Usually includes statements pertaining to importance 
         * criterion: impact, gap in care, and evidence.
         * </p>
         * 
         * @param rationale
         *     Detailed description of why the measure exists
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder rationale(Markdown rationale) {
            this.rationale = rationale;
            return this;
        }

        /**
         * <p>
         * Provides a summary of relevant clinical guidelines or other clinical recommendations supporting the measure.
         * </p>
         * 
         * @param clinicalRecommendationStatement
         *     Summary of clinical guidelines
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder clinicalRecommendationStatement(Markdown clinicalRecommendationStatement) {
            this.clinicalRecommendationStatement = clinicalRecommendationStatement;
            return this;
        }

        /**
         * <p>
         * Information on whether an increase or decrease in score is the preferred result (e.g., a higher score indicates better 
         * quality OR a lower score indicates better quality OR quality is within a range).
         * </p>
         * 
         * @param improvementNotation
         *     increase | decrease
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder improvementNotation(CodeableConcept improvementNotation) {
            this.improvementNotation = improvementNotation;
            return this;
        }

        /**
         * <p>
         * Provides a description of an individual term used within the measure.
         * </p>
         * 
         * @param definition
         *     Defined terms used in the measure documentation
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder definition(Markdown... definition) {
            for (Markdown value : definition) {
                this.definition.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Provides a description of an individual term used within the measure.
         * </p>
         * 
         * @param definition
         *     Defined terms used in the measure documentation
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder definition(Collection<Markdown> definition) {
            this.definition.addAll(definition);
            return this;
        }

        /**
         * <p>
         * Additional guidance for the measure including how it can be used in a clinical context, and the intent of the measure.
         * </p>
         * 
         * @param guidance
         *     Additional guidance for implementers
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder guidance(Markdown guidance) {
            this.guidance = guidance;
            return this;
        }

        /**
         * <p>
         * A group of population criteria for the measure.
         * </p>
         * 
         * @param group
         *     Population criteria group
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder group(Group... group) {
            for (Group value : group) {
                this.group.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A group of population criteria for the measure.
         * </p>
         * 
         * @param group
         *     Population criteria group
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder group(Collection<Group> group) {
            this.group.addAll(group);
            return this;
        }

        /**
         * <p>
         * The supplemental data criteria for the measure report, specified as either the name of a valid CQL expression within a 
         * referenced library, or a valid FHIR Resource Path.
         * </p>
         * 
         * @param supplementalData
         *     What other data should be reported with the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder supplementalData(SupplementalData... supplementalData) {
            for (SupplementalData value : supplementalData) {
                this.supplementalData.add(value);
            }
            return this;
        }

        /**
         * <p>
         * The supplemental data criteria for the measure report, specified as either the name of a valid CQL expression within a 
         * referenced library, or a valid FHIR Resource Path.
         * </p>
         * 
         * @param supplementalData
         *     What other data should be reported with the measure
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder supplementalData(Collection<SupplementalData> supplementalData) {
            this.supplementalData.addAll(supplementalData);
            return this;
        }

        @Override
        public Measure build() {
            return new Measure(this);
        }

        private Builder from(Measure measure) {
            id = measure.id;
            meta = measure.meta;
            implicitRules = measure.implicitRules;
            language = measure.language;
            text = measure.text;
            contained.addAll(measure.contained);
            extension.addAll(measure.extension);
            modifierExtension.addAll(measure.modifierExtension);
            url = measure.url;
            identifier.addAll(measure.identifier);
            version = measure.version;
            name = measure.name;
            title = measure.title;
            subtitle = measure.subtitle;
            experimental = measure.experimental;
            subject = measure.subject;
            date = measure.date;
            publisher = measure.publisher;
            contact.addAll(measure.contact);
            description = measure.description;
            useContext.addAll(measure.useContext);
            jurisdiction.addAll(measure.jurisdiction);
            purpose = measure.purpose;
            usage = measure.usage;
            copyright = measure.copyright;
            approvalDate = measure.approvalDate;
            lastReviewDate = measure.lastReviewDate;
            effectivePeriod = measure.effectivePeriod;
            topic.addAll(measure.topic);
            author.addAll(measure.author);
            editor.addAll(measure.editor);
            reviewer.addAll(measure.reviewer);
            endorser.addAll(measure.endorser);
            relatedArtifact.addAll(measure.relatedArtifact);
            library.addAll(measure.library);
            disclaimer = measure.disclaimer;
            scoring = measure.scoring;
            compositeScoring = measure.compositeScoring;
            type.addAll(measure.type);
            riskAdjustment = measure.riskAdjustment;
            rateAggregation = measure.rateAggregation;
            rationale = measure.rationale;
            clinicalRecommendationStatement = measure.clinicalRecommendationStatement;
            improvementNotation = measure.improvementNotation;
            definition.addAll(measure.definition);
            guidance = measure.guidance;
            group.addAll(measure.group);
            supplementalData.addAll(measure.supplementalData);
            return this;
        }
    }

    /**
     * <p>
     * A group of population criteria for the measure.
     * </p>
     */
    public static class Group extends BackboneElement {
        private final CodeableConcept code;
        private final String description;
        private final List<Population> population;
        private final List<Stratifier> stratifier;

        private Group(Builder builder) {
            super(builder);
            code = builder.code;
            description = builder.description;
            population = Collections.unmodifiableList(builder.population);
            stratifier = Collections.unmodifiableList(builder.stratifier);
        }

        /**
         * <p>
         * Indicates a meaning for the group. This can be as simple as a unique identifier, or it can establish meaning in a 
         * broader context by drawing from a terminology, allowing groups to be correlated across measures.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link CodeableConcept}.
         */
        public CodeableConcept getCode() {
            return code;
        }

        /**
         * <p>
         * The human readable description of this population group.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getDescription() {
            return description;
        }

        /**
         * <p>
         * A population criteria for the measure.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Population}.
         */
        public List<Population> getPopulation() {
            return population;
        }

        /**
         * <p>
         * The stratifier criteria for the measure report, specified as either the name of a valid CQL expression defined within 
         * a referenced library or a valid FHIR Resource Path.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Stratifier}.
         */
        public List<Stratifier> getStratifier() {
            return stratifier;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(code, "code", visitor);
                    accept(description, "description", visitor);
                    accept(population, "population", visitor, Population.class);
                    accept(stratifier, "stratifier", visitor, Stratifier.class);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return new Builder().from(this);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder extends BackboneElement.Builder {
            // optional
            private CodeableConcept code;
            private String description;
            private List<Population> population = new ArrayList<>();
            private List<Stratifier> stratifier = new ArrayList<>();

            private Builder() {
                super();
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * Indicates a meaning for the group. This can be as simple as a unique identifier, or it can establish meaning in a 
             * broader context by drawing from a terminology, allowing groups to be correlated across measures.
             * </p>
             * 
             * @param code
             *     Meaning of the group
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder code(CodeableConcept code) {
                this.code = code;
                return this;
            }

            /**
             * <p>
             * The human readable description of this population group.
             * </p>
             * 
             * @param description
             *     Summary description
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder description(String description) {
                this.description = description;
                return this;
            }

            /**
             * <p>
             * A population criteria for the measure.
             * </p>
             * 
             * @param population
             *     Population criteria
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder population(Population... population) {
                for (Population value : population) {
                    this.population.add(value);
                }
                return this;
            }

            /**
             * <p>
             * A population criteria for the measure.
             * </p>
             * 
             * @param population
             *     Population criteria
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder population(Collection<Population> population) {
                this.population.addAll(population);
                return this;
            }

            /**
             * <p>
             * The stratifier criteria for the measure report, specified as either the name of a valid CQL expression defined within 
             * a referenced library or a valid FHIR Resource Path.
             * </p>
             * 
             * @param stratifier
             *     Stratifier criteria for the measure
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder stratifier(Stratifier... stratifier) {
                for (Stratifier value : stratifier) {
                    this.stratifier.add(value);
                }
                return this;
            }

            /**
             * <p>
             * The stratifier criteria for the measure report, specified as either the name of a valid CQL expression defined within 
             * a referenced library or a valid FHIR Resource Path.
             * </p>
             * 
             * @param stratifier
             *     Stratifier criteria for the measure
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder stratifier(Collection<Stratifier> stratifier) {
                this.stratifier.addAll(stratifier);
                return this;
            }

            @Override
            public Group build() {
                return new Group(this);
            }

            private Builder from(Group group) {
                id = group.id;
                extension.addAll(group.extension);
                modifierExtension.addAll(group.modifierExtension);
                code = group.code;
                description = group.description;
                population.addAll(group.population);
                stratifier.addAll(group.stratifier);
                return this;
            }
        }

        /**
         * <p>
         * A population criteria for the measure.
         * </p>
         */
        public static class Population extends BackboneElement {
            private final CodeableConcept code;
            private final String description;
            private final Expression criteria;

            private Population(Builder builder) {
                super(builder);
                code = builder.code;
                description = builder.description;
                criteria = ValidationSupport.requireNonNull(builder.criteria, "criteria");
            }

            /**
             * <p>
             * The type of population criteria.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link CodeableConcept}.
             */
            public CodeableConcept getCode() {
                return code;
            }

            /**
             * <p>
             * The human readable description of this population criteria.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link String}.
             */
            public String getDescription() {
                return description;
            }

            /**
             * <p>
             * An expression that specifies the criteria for the population, typically the name of an expression in a library.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Expression}.
             */
            public Expression getCriteria() {
                return criteria;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(code, "code", visitor);
                        accept(description, "description", visitor);
                        accept(criteria, "criteria", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return new Builder(criteria).from(this);
            }

            public Builder toBuilder(Expression criteria) {
                return new Builder(criteria).from(this);
            }

            public static Builder builder(Expression criteria) {
                return new Builder(criteria);
            }

            public static class Builder extends BackboneElement.Builder {
                // required
                private final Expression criteria;

                // optional
                private CodeableConcept code;
                private String description;

                private Builder(Expression criteria) {
                    super();
                    this.criteria = criteria;
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * The type of population criteria.
                 * </p>
                 * 
                 * @param code
                 *     initial-population | numerator | numerator-exclusion | denominator | denominator-exclusion | denominator-exception | 
                 *     measure-population | measure-population-exclusion | measure-observation
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder code(CodeableConcept code) {
                    this.code = code;
                    return this;
                }

                /**
                 * <p>
                 * The human readable description of this population criteria.
                 * </p>
                 * 
                 * @param description
                 *     The human readable description of this population criteria
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder description(String description) {
                    this.description = description;
                    return this;
                }

                @Override
                public Population build() {
                    return new Population(this);
                }

                private Builder from(Population population) {
                    id = population.id;
                    extension.addAll(population.extension);
                    modifierExtension.addAll(population.modifierExtension);
                    code = population.code;
                    description = population.description;
                    return this;
                }
            }
        }

        /**
         * <p>
         * The stratifier criteria for the measure report, specified as either the name of a valid CQL expression defined within 
         * a referenced library or a valid FHIR Resource Path.
         * </p>
         */
        public static class Stratifier extends BackboneElement {
            private final CodeableConcept code;
            private final String description;
            private final Expression criteria;
            private final List<Component> component;

            private Stratifier(Builder builder) {
                super(builder);
                code = builder.code;
                description = builder.description;
                criteria = builder.criteria;
                component = Collections.unmodifiableList(builder.component);
            }

            /**
             * <p>
             * Indicates a meaning for the stratifier. This can be as simple as a unique identifier, or it can establish meaning in a 
             * broader context by drawing from a terminology, allowing stratifiers to be correlated across measures.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link CodeableConcept}.
             */
            public CodeableConcept getCode() {
                return code;
            }

            /**
             * <p>
             * The human readable description of this stratifier criteria.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link String}.
             */
            public String getDescription() {
                return description;
            }

            /**
             * <p>
             * An expression that specifies the criteria for the stratifier. This is typically the name of an expression defined 
             * within a referenced library, but it may also be a path to a stratifier element.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Expression}.
             */
            public Expression getCriteria() {
                return criteria;
            }

            /**
             * <p>
             * A component of the stratifier criteria for the measure report, specified as either the name of a valid CQL expression 
             * defined within a referenced library or a valid FHIR Resource Path.
             * </p>
             * 
             * @return
             *     A list containing immutable objects of type {@link Component}.
             */
            public List<Component> getComponent() {
                return component;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(code, "code", visitor);
                        accept(description, "description", visitor);
                        accept(criteria, "criteria", visitor);
                        accept(component, "component", visitor, Component.class);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return new Builder().from(this);
            }

            public static Builder builder() {
                return new Builder();
            }

            public static class Builder extends BackboneElement.Builder {
                // optional
                private CodeableConcept code;
                private String description;
                private Expression criteria;
                private List<Component> component = new ArrayList<>();

                private Builder() {
                    super();
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * Indicates a meaning for the stratifier. This can be as simple as a unique identifier, or it can establish meaning in a 
                 * broader context by drawing from a terminology, allowing stratifiers to be correlated across measures.
                 * </p>
                 * 
                 * @param code
                 *     Meaning of the stratifier
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder code(CodeableConcept code) {
                    this.code = code;
                    return this;
                }

                /**
                 * <p>
                 * The human readable description of this stratifier criteria.
                 * </p>
                 * 
                 * @param description
                 *     The human readable description of this stratifier
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder description(String description) {
                    this.description = description;
                    return this;
                }

                /**
                 * <p>
                 * An expression that specifies the criteria for the stratifier. This is typically the name of an expression defined 
                 * within a referenced library, but it may also be a path to a stratifier element.
                 * </p>
                 * 
                 * @param criteria
                 *     How the measure should be stratified
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder criteria(Expression criteria) {
                    this.criteria = criteria;
                    return this;
                }

                /**
                 * <p>
                 * A component of the stratifier criteria for the measure report, specified as either the name of a valid CQL expression 
                 * defined within a referenced library or a valid FHIR Resource Path.
                 * </p>
                 * 
                 * @param component
                 *     Stratifier criteria component for the measure
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder component(Component... component) {
                    for (Component value : component) {
                        this.component.add(value);
                    }
                    return this;
                }

                /**
                 * <p>
                 * A component of the stratifier criteria for the measure report, specified as either the name of a valid CQL expression 
                 * defined within a referenced library or a valid FHIR Resource Path.
                 * </p>
                 * 
                 * @param component
                 *     Stratifier criteria component for the measure
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder component(Collection<Component> component) {
                    this.component.addAll(component);
                    return this;
                }

                @Override
                public Stratifier build() {
                    return new Stratifier(this);
                }

                private Builder from(Stratifier stratifier) {
                    id = stratifier.id;
                    extension.addAll(stratifier.extension);
                    modifierExtension.addAll(stratifier.modifierExtension);
                    code = stratifier.code;
                    description = stratifier.description;
                    criteria = stratifier.criteria;
                    component.addAll(stratifier.component);
                    return this;
                }
            }

            /**
             * <p>
             * A component of the stratifier criteria for the measure report, specified as either the name of a valid CQL expression 
             * defined within a referenced library or a valid FHIR Resource Path.
             * </p>
             */
            public static class Component extends BackboneElement {
                private final CodeableConcept code;
                private final String description;
                private final Expression criteria;

                private Component(Builder builder) {
                    super(builder);
                    code = builder.code;
                    description = builder.description;
                    criteria = ValidationSupport.requireNonNull(builder.criteria, "criteria");
                }

                /**
                 * <p>
                 * Indicates a meaning for the stratifier component. This can be as simple as a unique identifier, or it can establish 
                 * meaning in a broader context by drawing from a terminology, allowing stratifiers to be correlated across measures.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link CodeableConcept}.
                 */
                public CodeableConcept getCode() {
                    return code;
                }

                /**
                 * <p>
                 * The human readable description of this stratifier criteria component.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getDescription() {
                    return description;
                }

                /**
                 * <p>
                 * An expression that specifies the criteria for this component of the stratifier. This is typically the name of an 
                 * expression defined within a referenced library, but it may also be a path to a stratifier element.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Expression}.
                 */
                public Expression getCriteria() {
                    return criteria;
                }

                @Override
                public void accept(java.lang.String elementName, Visitor visitor) {
                    if (visitor.preVisit(this)) {
                        visitor.visitStart(elementName, this);
                        if (visitor.visit(elementName, this)) {
                            // visit children
                            accept(id, "id", visitor);
                            accept(extension, "extension", visitor, Extension.class);
                            accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                            accept(code, "code", visitor);
                            accept(description, "description", visitor);
                            accept(criteria, "criteria", visitor);
                        }
                        visitor.visitEnd(elementName, this);
                        visitor.postVisit(this);
                    }
                }

                @Override
                public Builder toBuilder() {
                    return new Builder(criteria).from(this);
                }

                public Builder toBuilder(Expression criteria) {
                    return new Builder(criteria).from(this);
                }

                public static Builder builder(Expression criteria) {
                    return new Builder(criteria);
                }

                public static class Builder extends BackboneElement.Builder {
                    // required
                    private final Expression criteria;

                    // optional
                    private CodeableConcept code;
                    private String description;

                    private Builder(Expression criteria) {
                        super();
                        this.criteria = criteria;
                    }

                    /**
                     * <p>
                     * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                     * contain spaces.
                     * </p>
                     * 
                     * @param id
                     *     Unique id for inter-element referencing
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder id(java.lang.String id) {
                        return (Builder) super.id(id);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Extension... extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Collection<Extension> extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Extension... modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Collection<Extension> modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * Indicates a meaning for the stratifier component. This can be as simple as a unique identifier, or it can establish 
                     * meaning in a broader context by drawing from a terminology, allowing stratifiers to be correlated across measures.
                     * </p>
                     * 
                     * @param code
                     *     Meaning of the stratifier component
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder code(CodeableConcept code) {
                        this.code = code;
                        return this;
                    }

                    /**
                     * <p>
                     * The human readable description of this stratifier criteria component.
                     * </p>
                     * 
                     * @param description
                     *     The human readable description of this stratifier component
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder description(String description) {
                        this.description = description;
                        return this;
                    }

                    @Override
                    public Component build() {
                        return new Component(this);
                    }

                    private Builder from(Component component) {
                        id = component.id;
                        extension.addAll(component.extension);
                        modifierExtension.addAll(component.modifierExtension);
                        code = component.code;
                        description = component.description;
                        return this;
                    }
                }
            }
        }
    }

    /**
     * <p>
     * The supplemental data criteria for the measure report, specified as either the name of a valid CQL expression within a 
     * referenced library, or a valid FHIR Resource Path.
     * </p>
     */
    public static class SupplementalData extends BackboneElement {
        private final CodeableConcept code;
        private final List<CodeableConcept> usage;
        private final String description;
        private final Expression criteria;

        private SupplementalData(Builder builder) {
            super(builder);
            code = builder.code;
            usage = Collections.unmodifiableList(builder.usage);
            description = builder.description;
            criteria = ValidationSupport.requireNonNull(builder.criteria, "criteria");
        }

        /**
         * <p>
         * Indicates a meaning for the supplemental data. This can be as simple as a unique identifier, or it can establish 
         * meaning in a broader context by drawing from a terminology, allowing supplemental data to be correlated across 
         * measures.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link CodeableConcept}.
         */
        public CodeableConcept getCode() {
            return code;
        }

        /**
         * <p>
         * An indicator of the intended usage for the supplemental data element. Supplemental data indicates the data is 
         * additional information requested to augment the measure information. Risk adjustment factor indicates the data is 
         * additional information used to calculate risk adjustment factors when applying a risk model to the measure calculation.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link CodeableConcept}.
         */
        public List<CodeableConcept> getUsage() {
            return usage;
        }

        /**
         * <p>
         * The human readable description of this supplemental data.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getDescription() {
            return description;
        }

        /**
         * <p>
         * The criteria for the supplemental data. This is typically the name of a valid expression defined within a referenced 
         * library, but it may also be a path to a specific data element. The criteria defines the data to be returned for this 
         * element.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Expression}.
         */
        public Expression getCriteria() {
            return criteria;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(code, "code", visitor);
                    accept(usage, "usage", visitor, CodeableConcept.class);
                    accept(description, "description", visitor);
                    accept(criteria, "criteria", visitor);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return new Builder(criteria).from(this);
        }

        public Builder toBuilder(Expression criteria) {
            return new Builder(criteria).from(this);
        }

        public static Builder builder(Expression criteria) {
            return new Builder(criteria);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final Expression criteria;

            // optional
            private CodeableConcept code;
            private List<CodeableConcept> usage = new ArrayList<>();
            private String description;

            private Builder(Expression criteria) {
                super();
                this.criteria = criteria;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * Indicates a meaning for the supplemental data. This can be as simple as a unique identifier, or it can establish 
             * meaning in a broader context by drawing from a terminology, allowing supplemental data to be correlated across 
             * measures.
             * </p>
             * 
             * @param code
             *     Meaning of the supplemental data
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder code(CodeableConcept code) {
                this.code = code;
                return this;
            }

            /**
             * <p>
             * An indicator of the intended usage for the supplemental data element. Supplemental data indicates the data is 
             * additional information requested to augment the measure information. Risk adjustment factor indicates the data is 
             * additional information used to calculate risk adjustment factors when applying a risk model to the measure calculation.
             * </p>
             * 
             * @param usage
             *     supplemental-data | risk-adjustment-factor
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder usage(CodeableConcept... usage) {
                for (CodeableConcept value : usage) {
                    this.usage.add(value);
                }
                return this;
            }

            /**
             * <p>
             * An indicator of the intended usage for the supplemental data element. Supplemental data indicates the data is 
             * additional information requested to augment the measure information. Risk adjustment factor indicates the data is 
             * additional information used to calculate risk adjustment factors when applying a risk model to the measure calculation.
             * </p>
             * 
             * @param usage
             *     supplemental-data | risk-adjustment-factor
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder usage(Collection<CodeableConcept> usage) {
                this.usage.addAll(usage);
                return this;
            }

            /**
             * <p>
             * The human readable description of this supplemental data.
             * </p>
             * 
             * @param description
             *     The human readable description of this supplemental data
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder description(String description) {
                this.description = description;
                return this;
            }

            @Override
            public SupplementalData build() {
                return new SupplementalData(this);
            }

            private Builder from(SupplementalData supplementalData) {
                id = supplementalData.id;
                extension.addAll(supplementalData.extension);
                modifierExtension.addAll(supplementalData.modifierExtension);
                code = supplementalData.code;
                usage.addAll(supplementalData.usage);
                description = supplementalData.description;
                return this;
            }
        }
    }
}