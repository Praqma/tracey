package net.praqma.tracey.protocol;

import hudson.Extension;
import hudson.model.Descriptor;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class ProtocolConfiguration extends GlobalConfiguration {

    /**
     * Descriptor for {@link ProtocolConfiguration}. Used as a singleton. The class is marked as public so that it can be
     * accessed from views.
     * <p/>
     * <p/>
     * See <tt>views/hudson/plugins/tracey/ProtocolConfiguration/*.jelly</tt> for the actual HTML fragment for the
     * configuration screen.
     */
    @Extension
    // this marker indicates Hudson that this is an implementation of an extension point.
    public static final class DescriptorImpl extends Descriptor<GlobalConfiguration> {

        private String resourceName;

        public DescriptorImpl() {
            super(ProtocolConfiguration.class);
            load();
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        @Override
        public String getDisplayName() {
            return "Tracey Protocol configuration Plugin";
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            resourceName = formData.getString("resourceName");
            save();
            return super.configure(req, formData);
        }

        public String getResourceName() {
            return resourceName;
        }
    }
}