package com.example.contacts.presentation.feature.contactlist;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.contacts.R;
import com.example.contacts.databinding.ItemContactListContentBinding;
import com.example.contacts.databinding.ItemContactListHeaderBinding;

import java.util.HashMap;
import java.util.List;

public class ContactListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> contactListHeader;
    private HashMap<String, List<String>> contactListGroup;
    private ContactListViewModel contactListViewModel;

    public ContactListAdapter(Context context, List<String> contactListHeader,
                              HashMap<String, List<String>> contactListGroup,
                              ContactListViewModel contactListViewModel) {
        this.context = context;
        this.contactListHeader = contactListHeader;
        this.contactListGroup = contactListGroup;
        this.contactListViewModel = contactListViewModel;
    }

    public void updateAdapter(HashMap<String, List<String>> listGroup, List<String> listHeader) {
        contactListHeader.addAll(listHeader);
        contactListGroup.putAll(listGroup);
        notifyDataSetChanged();
    }

    public void releaseData() {
        contactListHeader.clear();
        contactListGroup.clear();
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.contactListGroup.get(this.contactListHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int listPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String address = (String) getChild(groupPosition, childPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemContactListContentBinding binding = ItemContactListContentBinding.inflate(layoutInflater);

        binding.tvContactBody.setText(address);
        binding.getRoot().setOnClickListener(v -> contactListViewModel.contactListItemClicked(groupPosition,true));

        return binding.getRoot();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.contactListGroup.get(this.contactListHeader.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.contactListHeader.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.contactListHeader.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String name = (String) getGroup(listPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemContactListHeaderBinding binding = ItemContactListHeaderBinding.inflate(layoutInflater);

        binding.tvContactName.setText(name);
        binding.imgIndicator.setImageResource(isExpanded ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);

        return binding.getRoot();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
