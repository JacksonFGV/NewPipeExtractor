package org.schabi.newpipe.extractor.search;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

public abstract class SearchExtractor extends ListExtractor<InfoItem> {

    public static class NothingFoundException extends ExtractionException {
        public NothingFoundException(String message) {
            super(message);
        }
    }

    private final InfoItemsSearchCollector collector;
    private final String contentCountry;

    public SearchExtractor(StreamingService service, SearchQueryUrlHandler urlIdHandler, String contentCountry) {
        super(service, urlIdHandler);
        collector = new InfoItemsSearchCollector(service.getServiceId());
        this.contentCountry = contentCountry;
    }

    public String getSearchString() {
        return getUrlIdHandler().getSearchString();
    }

    public abstract String getSearchSuggestion() throws ParsingException;

    protected InfoItemsSearchCollector getInfoItemSearchCollector() {
        return collector;
    }

    @Override
    public SearchQueryUrlHandler getUrlIdHandler() {
        return (SearchQueryUrlHandler) super.getUrlIdHandler();
    }

    @Override
    public String getName() {
        return getUrlIdHandler().getSearchString();
    }

    protected String getContentCountry() {
        return contentCountry;
    }
}
