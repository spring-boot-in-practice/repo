package com.manning.sbip.ch04.endpoint;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.jmx.annotation.JmxEndpoint;
import org.springframework.stereotype.Component;

import com.manning.sbip.ch04.model.ReleaseNote;

@Component
@Endpoint(id = "releaseNotes")
//@JmxEndpoint(id = "releaseNotes")
public class ReleaseNotesEndpoint {

	private final Collection<ReleaseNote> releaseNotes;

	@Autowired
	public ReleaseNotesEndpoint(Collection<ReleaseNote> releaseNotes) {
		this.releaseNotes = releaseNotes;
	}

	@ReadOperation
	public Iterable<ReleaseNote> releaseNotes() {
		return releaseNotes;
	}

	@ReadOperation
	public Object selectCourse(@Selector String version) {
		Optional<ReleaseNote> releaseNoteOptional = releaseNotes
				.stream()
				.filter(releaseNote -> version.equals(releaseNote.getVersion()))
				.findFirst();
		if(releaseNoteOptional.isPresent()) {
			return releaseNoteOptional.get();
		}
		return String.format("No such release version exists : %s", version);
	}
	
	@DeleteOperation
	public void removeReleaseVersion(@Selector String version) {
		Optional<ReleaseNote> releaseNoteOptional = releaseNotes
				.stream()
				.filter(releaseNote -> version.equals(releaseNote.getVersion()))
				.findFirst();
		if(releaseNoteOptional.isPresent()) {
			releaseNotes.remove(releaseNoteOptional.get());
		}
	}
}
